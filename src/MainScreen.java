import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainScreen extends JFrame {

    //Panel1 : User Registration
    JPanel subscriberPanel;
    JTextField subName;
    JTextField subLastName;
    JTextField subMobile;
    JTextField subCity;

    JLabel nameLBL;
    JLabel lastLBL;
    JLabel mobileLBL;
    JLabel cityLBL;

    //Panel 2 : Cycle

    JTextField startCycleFLD;
    JTextField endCycleFLD;
    JTextField numberTVFLD;
    JLabel todayLBL;
    JPanel cyclePanel;
    SimpleDateFormat df;
    Date currentDate;
    JLabel startCycleLBL;
    JLabel endCycleLBL;
    JLabel numberTVLBL;

    //Panel 3 : Channel's Packages
    JCheckBox sportsCHKBX;
    JCheckBox movieCHBX;
    JCheckBox docCHBX;
    JPanel packagePanel;

    //Panel 4 : Package Details
    JTextArea channelsAreaS;
    JTextArea channelsAreaM;
    JTextArea channelsAreaD;
    JPanel detailsPanel4;

    // Panel 5 :  Check and Payments
    JLabel installFeeLBL;
    JPanel feePanel;
    JLabel packageFeelBL;
    JLabel totalFeeLBL;

    // Panel 6 : Table (Data of Subscription)
    JTable table;
    DefaultTableModel tableModel;
    JPanel p6Panel;

    // Panel 7 : Action Panel
    JButton saveBTN;
    JButton loadBTN;
    JButton newBTN;
    JPanel p7ActionsPanel;

    //Classes and Objects
    Subscriber subscriber;
    Subscription subscription;

    int packagesSelectedPrice = 0;
    int totalPrice;


    //Saving Data

    ArrayList<Subscription> listToSave = new ArrayList<>();
    File file;

    // Constructor
    public MainScreen() throws HeadlessException {

        /*******************************Panel 1 ***********************************************/
        subscriberPanel = new JPanel();
        Border panel1Title = BorderFactory.createTitledBorder("Subscriber Details");
        subscriberPanel.setBorder(panel1Title);
        subscriberPanel.setBounds(15, 15, 300, 200);
        subscriberPanel.setLayout(new GridLayout(4, 2));


        //JLabel
        nameLBL = new JLabel("First Name : ");
        lastLBL = new JLabel("Last Name : ");
        mobileLBL = new JLabel("Mobile: ");
        cityLBL = new JLabel("City: ");


        //TextFields
        subName = new JTextField();
        subName.setOpaque(false);
        subLastName = new JTextField();
        subLastName.setOpaque(false);
        subMobile = new JTextField();
        subMobile.setOpaque(false);
        subCity = new JTextField();
        subCity.setOpaque(false);

        //Adding Components to Panel1
        subscriberPanel.add(nameLBL);
        subscriberPanel.add(subName);
        subscriberPanel.add(lastLBL);
        subscriberPanel.add(subLastName);
        subscriberPanel.add(mobileLBL);
        subscriberPanel.add(subMobile);
        subscriberPanel.add(cityLBL);
        subscriberPanel.add(subCity);

        /***********************************************Panel 2*******************************/
        cyclePanel = new JPanel();
        cyclePanel.setBounds(15, 250, 300, 500);
        cyclePanel.setLayout(new GridLayout(14, 1));

        Border cycleBorder = BorderFactory.createTitledBorder("Cycle Details");
        cyclePanel.setBorder(cycleBorder);

        // Components of cycle panel
        todayLBL = new JLabel();
        df = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = new Date();
        todayLBL.setText("Today: " + df.format(currentDate));

        //Start cycle Date
        startCycleLBL = new JLabel("Start Cycle Date (DD/MM/YYYY)");
        startCycleFLD = new JTextField();

        //End Cycle date
        endCycleLBL = new JLabel("End Cycle Date (DD/MM/YYYY)");
        endCycleFLD = new JTextField();

        // Number of TVs
        numberTVLBL = new JLabel("Number of TV: ");
        numberTVFLD = new JTextField();

        cyclePanel.add(todayLBL);
        cyclePanel.add(startCycleLBL);
        cyclePanel.add(startCycleFLD);
        cyclePanel.add(endCycleLBL);
        cyclePanel.add(endCycleFLD);
        cyclePanel.add(numberTVLBL);
        cyclePanel.add(numberTVFLD);


        //Make opacity for fields
        startCycleFLD.setOpaque(false);
        endCycleFLD.setOpaque(false);
        numberTVFLD.setOpaque(false);

        /*******************************Panel 3 ***********************************************/

        packagePanel = new JPanel();
        packagePanel.setBounds(330, 15, 300, 200);
        packagePanel.setLayout(new GridLayout(5, 1));

        Border packBorder = BorderFactory.createTitledBorder("Available Packages");
        packagePanel.setBorder(packBorder);

        JLabel packageLBL = new JLabel("Please select your Package : ");
        sportsCHKBX = new JCheckBox("Sports Package");
        movieCHBX = new JCheckBox("Movie Package");
        docCHBX = new JCheckBox("Documentary Package");

        JButton subscribeBTN = new JButton("Subscribe");

        packagePanel.add(packageLBL);
        packagePanel.add(sportsCHKBX);
        packagePanel.add(movieCHBX);
        packagePanel.add(docCHBX);
        packagePanel.add(subscribeBTN);


        sportsCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (sportsCHKBX.isSelected()) {
                    DisplaySportsChannels();

                    //Make Price Changes
                } else {

                    channelsAreaS.setText("");
                }
            }
        });

        movieCHBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (movieCHBX.isSelected()) {
                    DisplayMoviesChannels();
                } else {

                    channelsAreaM.setText("");
                }
            }
        });

        docCHBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (docCHBX.isSelected()) {
                    DisplayDocumentaryChannel();

                } else {
                    channelsAreaD.setText("");
                }
            }
        });
        subscribeBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GetSubScriberData();
                } catch (Exception se) {

                }
            }
        });

        /***********************************************Panel 4*******************************/

        detailsPanel4 = new JPanel();
        detailsPanel4.setLayout(new GridLayout(3, 1));
        detailsPanel4.setBounds(330, 230, 300, 500);

        Border p4Border = BorderFactory.createTitledBorder("Available Channel Details");
        detailsPanel4.setBorder(p4Border);

        channelsAreaS = new JTextArea(5, 1);
        channelsAreaS.setEditable(false);
        channelsAreaS.setOpaque(false);
        channelsAreaS.setLineWrap(true);

        channelsAreaM = new JTextArea(5, 1);
        channelsAreaM.setEditable(false);
        channelsAreaM.setOpaque(false);
        channelsAreaM.setLineWrap(true);

        channelsAreaD = new JTextArea(5, 1);
        channelsAreaD.setEditable(false);
        channelsAreaD.setOpaque(false);
        channelsAreaD.setLineWrap(true);

        detailsPanel4.add(channelsAreaS);
        detailsPanel4.add(channelsAreaM);
        detailsPanel4.add(channelsAreaD);

        /******************************* Panel 5 ***********************************************/

        feePanel = new JPanel();
        feePanel.setBounds(645, 15, 200, 200);
        feePanel.setLayout(new GridLayout(3, 1));

        Border blackLine5 = BorderFactory.createTitledBorder("Fee and Check");
        feePanel.setBorder(blackLine5);

        installFeeLBL = new JLabel("Installation Fee: ");
        packageFeelBL = new JLabel("Package Fee: ");
        totalFeeLBL = new JLabel("Total Amount to pay: ");

        feePanel.add(installFeeLBL);
        feePanel.add(packageFeelBL);
        feePanel.add(totalFeeLBL);

        /******************************* Panel 6 ***********************************************/

        p6Panel = new JPanel();
        p6Panel.setBounds(645, 230, 515, 500);
        p6Panel.setLayout(new GridLayout(3, 1));

        Border border6 = BorderFactory.createTitledBorder("Our Customers");
        p6Panel.setBorder(border6);

        // Table
        // 1- table Model

        tableModel = new DefaultTableModel();

        // 2- Columns
        table = new JTable(tableModel);
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Start Cycle");
        tableModel.addColumn("End Cycle");
        tableModel.addColumn("Total Fee");

        //3- Scroll Pane

        JScrollPane scrollPane = new JScrollPane(table);
        p6Panel.add(scrollPane);


        /*******************************Panel 7 ***********************************************/

        p7ActionsPanel = new JPanel();
        p7ActionsPanel.setBounds(860, 15, 300, 200);
        Border border7 = BorderFactory.createTitledBorder("Action Tab");
        p7ActionsPanel.setBorder(border7);
        p7ActionsPanel.setLayout(new GridLayout(4, 1));


        saveBTN = new JButton("Save Subscription");
        loadBTN = new JButton("Load Subscription");
        newBTN = new JButton("New Subscription");

        p7ActionsPanel.add(newBTN);
        p7ActionsPanel.add(saveBTN);
        p7ActionsPanel.add(loadBTN);

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveSubscriptionToDisk();
            }
        });

        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewSubscription();
            }
        });
        loadBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Subscription> k = LoadDataFromDisk();
            }
        });

        //Adding Panels to JFrame
        setLayout(null); //for setting null layout for JFrame
        add(subscriberPanel);//Panel 1
        add(cyclePanel);//Panel 2
        add(packagePanel);//Panel 3
        add(detailsPanel4);//Panel 4
        add(feePanel);//Panel 5
        add(p6Panel); // panel 6
        add(p7ActionsPanel); //Panel 7
    }


    /********************************METHODS ********************************/
    private int DisplaySportsChannels() {

        SportsChannel m1 = new SportsChannel("Star Sports", "English/Hindi", "Sports", 3);
        SportsChannel m2 = new SportsChannel("Star Cricket", "English/Hindi", "Sports", 6);
        SportsChannel m3 = new SportsChannel("Neo Sports", "English/Hindi", "Sports", 9);
        SportsChannel m4 = new SportsChannel("National TV", "English/Hindi", "Sports", 12);
        SportsChannel m5 = new SportsChannel("Sony Cricket", "English/Hindi", "Sports", 15);
        SportsChannel m6 = new SportsChannel("Hotstar", "English/Hindi", "Sports", 18);
        SportsChannel m7 = new SportsChannel("Jio Cricket", "English/Hindi", "Sports", 21);

        ArrayList<SportsChannel> sportsChannels = new ArrayList<>();
        sportsChannels.add(m1);
        sportsChannels.add(m2);
        sportsChannels.add(m3);
        sportsChannels.add(m4);
        sportsChannels.add(m5);
        sportsChannels.add(m6);
        sportsChannels.add(m7);


        String sportsChannelString = " ";
        int packagePrice = 0;

        for (int i = 0; i < sportsChannels.size(); i++) {
            sportsChannelString +=
                    "   " + sportsChannels.get(i).getChannelName() +
                            "   " + sportsChannels.get(i).getLanguage() +
                            "   " + sportsChannels.get(i).getPrice()
                            + "\n";

            packagePrice += sportsChannels.get(i).getPrice();

        }
        channelsAreaS.setText(sportsChannelString);

        return packagePrice;
    }


    private int DisplayMoviesChannels() {

        MovieChannel m1 = new MovieChannel("Sony", "English/Hindi", "Movies", 3);
        MovieChannel m2 = new MovieChannel("Sony Max", "Hindi", "Movies", 6);
        MovieChannel m3 = new MovieChannel("Sony Live", "English/Hindi", "Movies", 9);
        MovieChannel m4 = new MovieChannel("Start Gold", "English", "Movies", 12);
        MovieChannel m5 = new MovieChannel("Goldmines", "English/English", "Movies", 15);
        MovieChannel m6 = new MovieChannel("Dinchaak", "Hindi", "Movies", 18);
        MovieChannel m7 = new MovieChannel("B4U", "Hindi", "Movies", 21);

        ArrayList<MovieChannel> movieList = new ArrayList<>();
        movieList.add(m1);
        movieList.add(m2);
        movieList.add(m3);
        movieList.add(m4);
        movieList.add(m5);
        movieList.add(m6);
        movieList.add(m7);

        String movChannelString = " ";
        int packagePrice = 0;

        for (int i = 0; i < movieList.size(); i++) {
            movChannelString +=
                    "   " + movieList.get(i).getChannelName() +
                            "   " + movieList.get(i).getLanguage() +
                            "   " + movieList.get(i).getPrice()
                            + "\n";

            packagePrice += movieList.get(i).getPrice();
        }
        channelsAreaM.setText(movChannelString);
        return packagePrice;
    }

    private int DisplayDocumentaryChannel() {

        DocumentaryChannel m1 = new DocumentaryChannel("National Geographic Channel", "English", "Documentary", 3);
        DocumentaryChannel m2 = new DocumentaryChannel("National Geographic Adventure", "English/ Hindi ", "Documentary", 6);
        DocumentaryChannel m3 = new DocumentaryChannel("Nat Geo Junior", "Hindi/EnglishP", "Documentary", 9);
        DocumentaryChannel m4 = new DocumentaryChannel("National Geographic Wild", "Hindi/English", "Documentary", 12);
        DocumentaryChannel m5 = new DocumentaryChannel("Discovery Channel", "Hindi/English", "DOc", 15);
        DocumentaryChannel m6 = new DocumentaryChannel("Animal Planet", "Hindi/English", "Documentary", 18);

        ArrayList<DocumentaryChannel> documentaryChannels = new ArrayList<>();
        documentaryChannels.add(m1);
        documentaryChannels.add(m2);
        documentaryChannels.add(m3);
        documentaryChannels.add(m4);
        documentaryChannels.add(m5);
        documentaryChannels.add(m6);

        String docChannelString = " ";

        int packagePrice = 0;
        for (int i = 0; i < documentaryChannels.size(); i++) {
            docChannelString +=
                    "   " + documentaryChannels.get(i).getChannelName() +
                            "   " + documentaryChannels.get(i).getLanguage() +
                            "   " + documentaryChannels.get(i).getPrice()
                            + "\n";

            packagePrice += documentaryChannels.get(i).getPrice();
        }
        channelsAreaD.setText(docChannelString);
        return packagePrice;

    }

    private void GetSubScriberData() throws ParseException {

        Date currentDate = new Date();

        //Subscriber Data
        subscriber = new Subscriber(
                subName.getText(),
                subLastName.getText(),
                subCity.getText(),
                subMobile.getText());

        //Cycle

        Date startCycle = df.parse(startCycleFLD.getText());
        Date endCycle = df.parse(endCycleFLD.getText());

        SubscriptionCycle cycle = new SubscriptionCycle(
                df.format(startCycle),
                df.format(endCycle)
        );

        //Subscription

        subscription = new Subscription(
                Integer.parseInt(numberTVFLD.getText()),
                subscriber,
                cycle,
                df.format(currentDate)
        );

        installFeeLBL.setText("Installation Fee: " +
                subscription.getTotalFee() + "Rs");
        ShowPrice();
    }

    private void ShowPrice() {
        if (docCHBX.isSelected())
            packagesSelectedPrice += DisplayDocumentaryChannel();

         else if (movieCHBX.isSelected()) {
            packagesSelectedPrice += DisplayMoviesChannels();
        } else if (sportsCHKBX.isSelected()) {
            packagesSelectedPrice += DisplaySportsChannels();
        }

        packageFeelBL.setText("Packages Fee: " + packagesSelectedPrice + " Rs");
        totalPrice = subscription.getTotalFee() + packagesSelectedPrice;
        totalFeeLBL.setText("Total Amount to pay :" + totalPrice + "Rs");

    }

    private ArrayList<Subscription> LoadDataFromDisk() {
        ArrayList<Subscription> s = new ArrayList<>();
        file = new File("d:\\myfile.dat");

        try {
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);

            s = (ArrayList) ois.readObject();
            ois.close();
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        for (Subscription sub : s) {
            DisplaySubscriptionInTable(sub);
        }
        return s;
    }

    private void DisplaySubscriptionInTable(Subscription sub) {
        // Displaying Data into the table
        tableModel.addRow(new Object[]
                {
                        sub.getSubscriber().getfName(),
                        sub.getSubscriber().getlName(),
                        sub.getSubscriber().getPhone(),
                        sub.getCycle().getStartDate(),
                        sub.getCycle().getEndDate(),
                        sub.getTotalFee()
                });
    }

    private void NewSubscription() {

        //All Fields are empty

        subName.setText("");
        subLastName.setText("");
        subCity.setText("");
        subMobile.setText("");

        startCycleFLD.setText("");
        endCycleFLD.setText("");
        numberTVFLD.setText("");


        installFeeLBL.setText("Installation Fee : ");
        packageFeelBL.setText("Package Fee: ");
        totalFeeLBL.setText("Total Amount to pay: ");

        movieCHBX.setSelected(false);
        docCHBX.setSelected(false);
        sportsCHKBX.setSelected(false);
    }


    private void SaveSubscriptionToDisk() {

        listToSave.add(subscription);
        file = new File("d:\\myfile.dat");

        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            // Saving the list of subscriptions
            oos.writeObject(listToSave);
            oos.flush();
            oos.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
        mainScreen.setBounds(20, 10, 1200, 800);

    }
}
