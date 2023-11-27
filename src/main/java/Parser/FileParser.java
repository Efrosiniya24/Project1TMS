package Parser;

import Model.Account;
import Model.Files;
import Pattern.AccountPattern;
import Pattern.DatePattern;
import Pattern.MoneyPattern;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Model.Files.files;

public class FileParser {
    private static final String PATH = "src/main/resources/input/";
    private static final String PATHARCHIVE = "src/main/resources/archive/";
    private static final StringBuilder ERRORreciever = new StringBuilder("Отсутствуют данные получателя");
    private static final StringBuilder ERRORSender = new StringBuilder("Отсутствуют данные получателя");
    private static final StringBuilder ERRORDate = new StringBuilder("Неправильно введена дата");
    private static final StringBuilder ERRORNumberAccountSender = new StringBuilder("Неправильно введен номер счета");
    private static final StringBuilder ERRORNumberAccountReceiver = new StringBuilder("Неправильно введен номер счета");
    private static final StringBuilder ERRORMoney = new StringBuilder("Неправильно введена сумма для перевода");
    private static List<Account> accounts = new ArrayList<>();

    public static void readFiles() throws ParserConfigurationException, IOException, SAXException {
        for (int i = 0; i < files.size(); i++) {
            StringBuilder transferTO;
            StringBuilder transferFrom;
            StringBuilder amountOfMoney;
            StringBuilder NumberOfAccount;
            StringBuilder date;
            StringBuilder name;
            StringBuilder city;

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(PATH + files.get(i));
            Node root = document.getDocumentElement();
            NodeList inf = root.getChildNodes();
            for (int u = 0; u < inf.getLength(); u++) {  //<NumberTransfer num = "??">
                Account account = new Account();
                List<StringBuilder> marks = new ArrayList<>();
                account.setFile(files.get(i));
                Node inf1 = inf.item(u);
                if (inf1.getNodeType() != Node.TEXT_NODE) {
                    NodeList inf0 = inf1.getChildNodes();       //Sender and Reciever
                    if (inf0.getLength() == 1) {
                        if (inf0.item(0).getNodeName().equals("Sender")) {
                            marks.add(ERRORreciever);
                        } else {
                            marks.add(ERRORSender);
                        }
                    } else {
                        for (int i0 = 0; i0 < inf0.getLength(); i0++) {
                            NodeList inf3 = inf0.item(i0).getChildNodes();  // необходимые теги
                            for (int k = 0; k < inf3.getLength(); k++) {
                                Node info = inf3.item(k);

                                switch (info.getNodeName()) {
                                    case "date" -> {
                                        if (!DatePattern.datePatternType(info.getTextContent()))
                                            marks.add(ERRORDate);
                                        account.setDate(info.getTextContent());
                                    }
                                    case "accountNumber" -> {
                                        if (!AccountPattern.accountPatternSize(info.getTextContent()))
                                            marks.add(ERRORNumberAccountSender);
                                        account.setNumberOfAccount(info.getTextContent());
                                    }
                                    case "AmountOfMoney" -> {
                                        if (!MoneyPattern.money(info.getTextContent()))
                                            marks.add(ERRORMoney);
                                        account.setAmountOfMoney(Integer.parseInt(info.getTextContent()));
                                    }
                                    case "TransferAmount" -> {
                                        if (!AccountPattern.moneyPattern(info.getTextContent()))
                                            marks.add(ERRORMoney);
                                        account.setAmountTransfer(Integer.parseInt(info.getTextContent()));
                                    }
                                    case "accountNumberReceiver" -> {
                                        if (!AccountPattern.accountPatternSize(info.getTextContent()))
                                            if (!marks.contains(ERRORNumberAccountSender))
                                                marks.add(ERRORNumberAccountReceiver);
                                        account.setNumberOfAccountTransfer(info.getTextContent());
                                    }

                                }
                            }
                        }
                    }
                }
                account.setMarks(marks);
                accounts.add(account);
            }
        }
    }

    public static void createFile() throws IOException {
        try (FileWriter fileWriter = new FileWriter(PATHARCHIVE + "NewFile.txt")) {
            for (Account account : accounts) {
                StringBuilder line = new StringBuilder();
                if (account.getMarks().isEmpty()) {
                    line = new StringBuilder(" успешно обработан");
                } else {
                    line = new StringBuilder(String.valueOf(account.getMarks()));
                }
                fileWriter.write(account.getDate() + " | " + account.getFile() + " перевод с "
                        + account.getNumberOfAccount() + " на " + account.getNumberOfAccountTransfer() +
                        " " + account.getAmountTransfer() + " " + line + "\n");
            }
            fileWriter.flush();
        }
    }

    public static void readFile() {
        String text;
        try (FileReader fileReader = new FileReader(PATHARCHIVE + "NewFile.txt")) {
            StringBuilder stringBuilder = new StringBuilder();
            int i;
            while ((i = fileReader.read()) != -1) {
                stringBuilder.append((char) i);
            }
            text = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(text);
    }

    private static boolean isAllowedTag(String tag) {
        return tag.startsWith("<Transfer>") ||
                tag.startsWith("<NumberTransfer>") ||
                tag.startsWith("<Sender>") ||
                tag.startsWith("<date>") ||
                tag.startsWith("<accountNumber>") ||
                tag.startsWith("<TransferAmount>") ||
                tag.startsWith("<Receiver>") ||
                tag.startsWith("<accountNumberReceiver>") ||
                tag.startsWith("<?xml version=\"1.0\"?>") ||
                tag.startsWith(" <accountNumberReceiver>");

    }

    public static void newFiles() throws FileNotFoundException {
        for (String file : files) {
            File outputFolder = new File(file);
            if (!outputFolder.exists()) {
                outputFolder.mkdir();
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(PATH +file));
                 BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/archive/" + file))) {
                String line;
                StringBuilder output = new StringBuilder();

                while ((line=reader.readLine()) != null) {
                    Pattern pattern = Pattern.compile("((<\\w+>)(.*?)(</\\w+>))");
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        String tag = matcher.group(1);
                        if (isAllowedTag(tag)) {
                            output.append(tag).append("\n");
                        }
                    }
                }
                writer.write(output.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}