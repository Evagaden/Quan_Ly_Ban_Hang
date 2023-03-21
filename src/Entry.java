import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Entry extends JFrame implements ActionListener {
    private JFrame frame;
    private JPanel panelToolBar;
    private JPanel khoPanel;
    private JPanel nhapPanel;
    private JPanel xuatPanel;
    private JPanel noPanel;
    private JToolBar toolBar;
    private JButton nutKho;
    private JButton nutNhap;
    private JButton nutXuat;
    private JButton nutNo;

    private Warehouse a;
    private JButton[] goods;

    private JButton[] exp;
    private JButton[] imp;
    private JButton[] debt;
    private int finalI;
    private int expIndex;
    private int impIndex;
    private int debtIndex;

    /*
    private final Path pathFile = Paths.get("src/resource/File_txt");
    private final Path pathBan = Paths.get("src/resource/Ban_Hang");
    private final Path pathNhap = Paths.get("src/resource/Nhap_Hang");
    private final Path pathI = Paths.get("src/resource/image");
    private final Path pathIG = Paths.get("src/resource/image_goods");
    private final Path pathNo = Paths.get("src/resource/No");
     */

    String pathFile = null;//"C:\\For Code\\Java\\Code\\Quan_Ly_Ban_Hang_1\\src\\res\\File_txt";
    String pathBan = null;//"C:\\For Code\\Java\\Code\\Quan_Ly_Ban_Hang_1\\src\\res\\Ban_Hang";
    String pathNhap = null;//"C:\\For Code\\Java\\Code\\Quan_Ly_Ban_Hang_1\\src\\res\\Nhap_Hang";
    String pathI = null;//"C:\\For Code\\Java\\Code\\Quan_Ly_Ban_Hang_1\\src\\res\\image";
    String pathIG = null;//"C:\\For Code\\Java\\Code\\Quan_Ly_Ban_Hang_1\\src\\res\\image_goods";
    String pathNo = null;//"C:\\For Code\\Java\\Code\\Quan_Ly_Ban_Hang_1\\src\\res\\No";
    public Entry() throws IOException, URISyntaxException {
        File file = new File("C:\\Quan_Ly_Ban_Hang\\File_txt");
        if(!file.exists())
        {
            file.mkdirs();
        }

        File file1 = new File("C:\\Quan_Ly_Ban_Hang\\Ban_Hang");
        if(!file1.exists())
        {
            file1.mkdirs();
        }

        File file2 = new File("C:\\Quan_Ly_Ban_Hang\\Nhap_Hang");
        if(!file2.exists())
        {
            file2.mkdirs();
        }

        File file3 = new File("C:\\Quan_Ly_Ban_Hang\\No");
        if(!file.exists())
        {
            file3.mkdirs();
        }

        File file4 = new File("C:\\Quan_Ly_Ban_Hang\\image_goods");
        if(!file4.exists())
        {
            file4.mkdirs();
        }

        pathFile = "C:\\Quan_Ly_Ban_Hang\\File_txt";
        pathBan = "C:\\Quan_Ly_Ban_Hang\\Ban_Hang";
        pathNhap = "C:\\Quan_Ly_Ban_Hang\\Nhap_Hang";
        pathIG = "C:\\Quan_Ly_Ban_Hang\\image_goods";
        pathNo = "C:\\Quan_Ly_Ban_Hang\\No";

        //set Warehouse
        a = new Warehouse();
        goods = new JButton[a.getAmountOfObjects()];
        //set panel
        panelToolBar = new JPanel();

        //set frame
        frame = new JFrame();
        frame.setSize(1200,700);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setTitle("Quan Ly Ban Hang");
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.jpg"));
        frame.setIconImage(icon.getImage());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set font;
        Font font = new Font("TimesNewRoman",Font.BOLD,10);

        //set button;
        nutKho = new JButton("KHO");
        nutKho.setFont(font);
        nutKho.setForeground(Color.white);
        nutKho.setBorderPainted(false);
        nutKho.setContentAreaFilled(false);
        nutKho.setSize(50,20);
        ImageIcon imgcKho = new ImageIcon(getClass().getClassLoader().getResource("kho_icon.png"));
        Image imgKho = imgcKho.getImage();
        imgKho = imgKho.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Icon iconKho = new ImageIcon(imgKho);
        nutKho.setIcon(iconKho);
        nutKho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                khoPanel.setVisible(true);
                nhapPanel.setVisible(false);
                xuatPanel.setVisible(false);
                noPanel.setVisible(false);
            }
        });

        nutNhap = new JButton("NHAP HANG");
        nutNhap.setFont(font);
        nutNhap.setForeground(Color.white);
        nutNhap.setBorderPainted(false);
        nutNhap.setContentAreaFilled(false);
        nutNhap.setSize(50,20);
        ImageIcon imgcNap = new ImageIcon(getClass().getClassLoader().getResource("nap_icon.png"));
        Image imgNap = imgcNap.getImage();
        imgNap = imgNap.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Icon iconNap = new ImageIcon(imgNap);
        nutNhap.setIcon(iconNap);
        nutNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                khoPanel.setVisible(false);
                nhapPanel.setVisible(true);
                xuatPanel.setVisible(false);
                noPanel.setVisible(false);
            }
        });

        nutXuat = new JButton("BAN HANG");
        nutXuat.setFont(font);
        nutXuat.setForeground(Color.white);
        nutXuat.setBorderPainted(false);
        nutXuat.setContentAreaFilled(false);
        nutXuat.setSize(50,20);
        ImageIcon imgcXuat = new ImageIcon(getClass().getClassLoader().getResource("xuat_icon.png"));
        Image imgXuat = imgcXuat.getImage();
        imgXuat = imgXuat.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Icon iconXuat = new ImageIcon(imgXuat);
        nutXuat.setIcon(iconXuat);
        nutXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                khoPanel.setVisible(false);
                nhapPanel.setVisible(false);
                xuatPanel.setVisible(true);
                noPanel.setVisible(false);
            }
        });

        nutNo = new JButton("TIEN NO");
        nutNo.setFont(font);
        nutNo.setForeground(Color.white);
        nutNo.setBorderPainted(false);
        nutNo.setContentAreaFilled(false);
        nutNo.setSize(50,20);
        ImageIcon imgcNo = new ImageIcon(getClass().getClassLoader().getResource("no_icon.png"));
        Image imgNo = imgcNo.getImage();
        imgNo = imgNo.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Icon iconNo = new ImageIcon(imgNo);
        nutNo.setIcon(iconNo);
        nutNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                khoPanel.setVisible(false);
                nhapPanel.setVisible(false);
                xuatPanel.setVisible(false);
                noPanel.setVisible(true);
            }
        });

        //set toolbar;
        toolBar = new JToolBar();
        toolBar.add(nutKho);
        toolBar.add(nutNhap);
        toolBar.add(nutXuat);
        toolBar.add(nutNo);
        toolBar.setBounds(0,0,400,40);
        toolBar.setBackground(new Color(0,166,202));

        //create panelToolBar;
        panelToolBar.setLayout(null);
        panelToolBar.setBounds(0,0,1200,40);
        panelToolBar.setBackground(new Color(0,166,202));
        panelToolBar.add(toolBar);


        //create khoPanel;
        khoPanel = new JPanel();
        khoPanel.setLayout(null);
        khoPanel.setBounds(0,40,1200,660);
        khoPanel.setBackground(Color.white);
        createKhoPanel();

        //create nhapPanel;
        nhapPanel = new JPanel();
        nhapPanel.setLayout(null);
        nhapPanel.setBounds(0,40,1200,660);
        nhapPanel.setBackground(Color.white);
        createNhapPanel();

        //create xuatPanel;
        xuatPanel = new JPanel();
        xuatPanel.setLayout(null);
        xuatPanel.setBounds(0,40,1200,660);
        xuatPanel.setBackground(Color.white);
        createXuatPanel();

        //create noPanel;
        noPanel = new JPanel();
        noPanel.setLayout(null);
        noPanel.setBounds(0,40,1200,660);
        noPanel.setBackground(Color.white);
        createNoPanel();

        frame.add(khoPanel);
        frame.add(nhapPanel);
        nhapPanel.setVisible(false);
        frame.add(xuatPanel);
        xuatPanel.setVisible(false);
        frame.add(noPanel);
        noPanel.setVisible(false);
        frame.add(panelToolBar);
        frame.setVisible(true);
    }

    public void createKhoPanel()
    {
        //create goodsPanel
        JPanel goodsPanel = new JPanel();
        goodsPanel.setLayout(null);
        goodsPanel.setBounds(10,60,810,550);
        goodsPanel.setBackground(new Color(0,220,255));

        Font font1 = new Font("TimesNewRoman", Font.BOLD, 13);

        //create button of goods
        for(int i = 0; i < a.getAmountOfObjects(); i++) {
            goods[i] = new JButton();
            goods[i].setLayout(null);
            goods[i].setBorder(new Border() {
                @Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                    g.setColor(new Color(68, 187, 139));
                    g.fillRoundRect(x, y, width, height, 20, 20);
                }

                @Override
                public Insets getBorderInsets(Component c) {
                    return new Insets(10, 10, 10, 10);
                }

                @Override
                public boolean isBorderOpaque() {
                    return true;
                }
            });
            goods[i].setSize(120, 60);
            goods[i].setBackground(new Color(0, 220, 255));

            JLabel labelName = new JLabel(a.getObject(i).getName());
            JLabel labelAmount = new JLabel(String.valueOf(a.getObject(i).getQuantity()) + " " + a.getObject(i).getUnit());
            JLabel labelAskPrice = new JLabel(String.valueOf(a.getObject(i).getAsk_prices()));
            labelName.setFont(font1);
            labelName.setForeground(Color.WHITE);
            labelName.setBounds(45, 10, 75, 15);
            labelAmount.setFont(font1);
            labelAmount.setForeground(Color.WHITE);
            labelAmount.setBounds(45, 25, 75, 15);
            labelAskPrice.setFont(font1);
            labelAskPrice.setForeground(Color.WHITE);
            labelAskPrice.setBounds(45, 40, 75, 15);
            goods[i].add(labelName);
            goods[i].add(labelAmount);
            goods[i].add(labelAskPrice);

            ImageIcon imgcButton = new ImageIcon(a.getObject(i).getPathPic());
            Image imgButton = imgcButton.getImage();
            imgButton = imgButton.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            Icon iconButton = new ImageIcon(imgButton);
            JLabel labelPic = new JLabel(iconButton);
            labelPic.setBounds(2, 10, 40, 40);
            goods[i].add(labelPic);
            goods[i].setLocation((i % 6) * 130 + 10, (i / 6) * 70 + 10);
            goodsPanel.add(goods[i]);
        }

        //create tkTF
        JLabel tkLB = new JLabel("Tim Kiem Theo Ten");
        tkLB.setBounds(10,30,120,20);
        JTextField tkTF = new JTextField();
        tkTF.setFont(font1);
        tkTF.setForeground(new Color(82,85,87));
        tkTF.setBounds(130,30,300,20);tkTF.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            int location = 0;
            String text = tkTF.getText();
            if(text.trim().length() == 0)
            {
                for(int i = 0; i < a.getAmountOfObjects(); i++)
                {
                    goods[i].setLocation((i % 6) * 130 + 10, (i / 6) * 70 + 10);
                    goods[i].setVisible(true);
                }
            }
            else
            {
                for(int i = 0; i < a.getAmountOfObjects(); i++)
                {
                    if(a.getObject(i).getName().indexOf(text) != -1 || a.getObject(i).getId().indexOf(text) != -1)
                    {
                        goods[i].setLocation((location % 6) * 130 + 10, (location / 6) * 70 + 10);
                        goods[i].setVisible(true);
                        location++;
                    }
                    else
                    {
                        goods[i].setVisible(false);
                    }
                }
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            int location = 0;
            String text = tkTF.getText();
            if(text.trim().length() == 0)
            {
                for(int i = 0; i < a.getAmountOfObjects(); i++)
                {
                    goods[i].setLocation((i % 6) * 130 + 10, (i / 6) * 70 + 10);
                    goods[i].setVisible(true);
                }
            }
            else
            {
                for(int i = 0; i < a.getAmountOfObjects(); i++)
                {
                    if(a.getObject(i).getName().indexOf(text) != -1 || a.getObject(i).getId().indexOf(text) != -1)
                    {
                        goods[i].setLocation((location % 6) * 130 + 10, (location / 6) * 70 + 10);
                        goods[i].setVisible(true);
                        location++;
                    }
                    else
                    {
                        goods[i].setVisible(false);
                    }
                }
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            int location = 0;
            String text = tkTF.getText();
            if(text.trim().length() == 0)
            {
                for(int i = 0; i < a.getAmountOfObjects(); i++)
                {
                    goods[i].setLocation((i % 6) * 130 + 10, (i / 6) * 70 + 10);
                    goods[i].setVisible(true);
                }
            }
            else
            {
                for(int i = 0; i < a.getAmountOfObjects(); i++)
                {
                    if(a.getObject(i).getName().indexOf(text) != -1 || a.getObject(i).getId().indexOf(text) != -1)
                    {
                        goods[i].setLocation((location % 6) * 130 + 10, (location / 6) * 70 + 10);
                        goods[i].setVisible(true);
                        location++;
                    }
                    else
                    {
                        goods[i].setVisible(false);
                    }
                }
            }
        }
    });

        JLabel tkLB2 = new JLabel("So Luong Toi Da");
        tkLB2.setBounds(500,30,100,20);
        JTextField tkTF2 = new JTextField();
        tkTF2.setFont(font1);
        tkTF2.setForeground(new Color(82,85,87));
        tkTF2.setBounds(610,30,100,20);tkTF2.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e){
            int location = 0;
            String text = tkTF2.getText();
            int quantity;
            try
            {
                quantity = Integer.valueOf(text);
            }
            catch (Exception ex)
            {
                quantity = 1000000000;
            }
            for(int i = 0; i < a.getAmountOfObjects(); i++)
            {
                if(a.getObject(i).getQuantity() <= quantity)
                {
                    goods[i].setLocation((location % 6) * 130 + 10, (location / 6) * 70 + 10);
                    goods[i].setVisible(true);
                    location++;
                }
                else
                {
                    goods[i].setVisible(false);
                }
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            int location = 0;
            String text = tkTF2.getText();
            int quantity;
            try
            {
                quantity = Integer.valueOf(text);
            }
            catch (Exception ex)
            {
                quantity = 1000000000;
            }
            for(int i = 0; i < a.getAmountOfObjects(); i++)
            {
                if(a.getObject(i).getQuantity() <= quantity)
                {
                    goods[i].setLocation((location % 6) * 130 + 10, (location / 6) * 70 + 10);
                    goods[i].setVisible(true);
                    location++;
                }
                else
                {
                    goods[i].setVisible(false);
                }
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            int location = 0;
            String text = tkTF2.getText();
            int quantity;
            try
            {
                quantity = Integer.valueOf(text);
            }
            catch (Exception ex)
            {
                quantity = 1000000000;
            }
            for(int i = 0; i < a.getAmountOfObjects(); i++)
            {
                if(a.getObject(i).getQuantity() <= quantity)
                {
                    goods[i].setLocation((location % 6) * 130 + 10, (location / 6) * 70 + 10);
                    goods[i].setVisible(true);
                    location++;
                }
                else
                {
                    goods[i].setVisible(false);
                }
            }
        }
    });


        //createviewPanel
        Font font2 = new Font("TimesNewRoman", Font.BOLD, 20);
        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(null);
        viewPanel.setBounds(830,60,350,550);
        viewPanel.setBackground(new Color(0,220,255));

        JPanel displayPicTF = new JPanel();
        displayPicTF.setLayout(null);
        displayPicTF.setBackground(Color.WHITE);
        displayPicTF.setBounds(125,30,100,100);

        JLabel pic = new JLabel();
        pic.setBounds(0,0,100,100);

        displayPicTF.add(pic);

        JLabel name = new JLabel("Ten");
        name.setFont(font2);
        name.setForeground(Color.WHITE);
        name.setBounds(30, 150, 100,20);

        JTextField displayName = new JTextField();
        displayName.setFont(font1);
        displayName.setBounds(140,150,200,20);

        JLabel id = new JLabel("Ma");
        id.setFont(font2);
        id.setForeground(Color.white);
        id.setBounds(30, 180, 100,20);

        JTextField displayID = new JTextField();
        displayID.setFont(font1);
        displayID.setBounds(140,180,200,20);

        JLabel quantity = new JLabel("So Luong");
        quantity.setFont(font2);
        quantity.setForeground(Color.white);
        quantity.setBounds(30, 210, 100,20);

        JTextField displayQuantity = new JTextField();
        displayQuantity.setFont(font1);
        displayQuantity.setBounds(140,210,200,20);

        JLabel unit = new JLabel("Don Vi");
        unit.setFont(font2);
        unit.setForeground(Color.white);
        unit.setBounds(30, 240, 100,20);

        JTextField displayUnit = new JTextField();
        displayUnit.setFont(font1);
        displayUnit.setBounds(140,240,200,20);

        JLabel bid_prices = new JLabel("Gia Mua");
        bid_prices.setFont(font2);
        bid_prices.setForeground(Color.WHITE);
        bid_prices.setBounds(30, 270, 100,20);

        JTextField displayBidPrices = new JTextField();
        displayBidPrices.setFont(font1);
        displayBidPrices.setBounds(140,270,200,20);

        JLabel ask_prices = new JLabel("Gia Ban");
        ask_prices.setFont(font2);
        ask_prices.setForeground(Color.white);
        ask_prices.setBounds(30, 300, 100,20);

        JTextField displayAskPrices = new JTextField();
        displayAskPrices.setFont(font1);
        displayAskPrices.setBounds(140,300,200,20);

        JButton xoaButton = new JButton("Xoa");
        xoaButton.setFont(font2);
        xoaButton.setBounds(90,330,80,40);
        xoaButton.setForeground(Color.BLACK);
        xoaButton.setBackground(Color.white);
        xoaButton.setFocusPainted(false);

        JButton luuButton = new JButton("Luu");
        luuButton.setFont(font2);
        luuButton.setBounds(200,330,80,40);
        luuButton.setForeground(Color.BLACK);
        luuButton.setBackground(Color.white);
        luuButton.setFocusPainted(false);

        viewPanel.add(displayPicTF);
        viewPanel.add(name);
        viewPanel.add(displayName);
        viewPanel.add(id);
        viewPanel.add(displayID);
        viewPanel.add(quantity);
        viewPanel.add(displayQuantity);
        viewPanel.add(unit);
        viewPanel.add(displayUnit);
        viewPanel.add(bid_prices);
        viewPanel.add(displayBidPrices);
        viewPanel.add(ask_prices);
        viewPanel.add(displayAskPrices);
        viewPanel.add(xoaButton);
        viewPanel.add(luuButton);

        //create themPanel
        JPanel themPanel = new JPanel();
        themPanel.setLayout(null);
        themPanel.setBounds(830,60,350,550);
        themPanel.setBackground(new Color(0,220,255));

        JPanel displayPicTF1 = new JPanel();
        displayPicTF1.setLayout(null);
        displayPicTF1.setBackground(Color.WHITE);
        displayPicTF1.setBounds(125,30,100,100);

        JLabel pic1 = new JLabel();
        pic1.setBounds(0,0,100,100);

        displayPicTF1.add(pic1);

        JLabel name1 = new JLabel("Ten");
        name1.setFont(font2);
        name1.setForeground(Color.WHITE);
        name1.setBounds(30, 150, 100,20);

        JTextField displayName1 = new JTextField();
        displayName1.setFont(font1);
        displayName1.setBounds(140,150,200,20);

        JLabel id1 = new JLabel("Ma");
        id1.setFont(font2);
        id1.setForeground(Color.white);
        id1.setBounds(30, 180, 100,20);

        JTextField displayID1 = new JTextField();
        displayID1.setFont(font1);
        displayID1.setBounds(140,180,200,20);

        JLabel quantity1 = new JLabel("So Luong");
        quantity1.setFont(font2);
        quantity1.setForeground(Color.white);
        quantity1.setBounds(30, 210, 100,20);

        JTextField displayQuantity1 = new JTextField();
        displayQuantity1.setFont(font1);
        displayQuantity1.setBounds(140,210,200,20);

        JLabel unit1 = new JLabel("Don Vi");
        unit1.setFont(font2);
        unit1.setForeground(Color.white);
        unit1.setBounds(30, 240, 100,20);

        JTextField displayUnit1 = new JTextField();
        displayUnit1.setFont(font1);
        displayUnit1.setBounds(140,240,200,20);

        JLabel bid_prices1 = new JLabel("Gia Mua");
        bid_prices1.setFont(font2);
        bid_prices1.setForeground(Color.WHITE);
        bid_prices1.setBounds(30, 270, 100,20);

        JTextField displayBidPrices1 = new JTextField();
        displayBidPrices1.setFont(font1);
        displayBidPrices1.setBounds(140,270,200,20);

        JLabel ask_prices1 = new JLabel("Gia Ban");
        ask_prices1.setFont(font2);
        ask_prices1.setForeground(Color.white);
        ask_prices1.setBounds(30, 300, 100,20);

        JTextField displayAskPrices1 = new JTextField();
        displayAskPrices1.setFont(font1);
        displayAskPrices1.setBounds(140,300,200,20);

        JButton luuButton1 = new JButton("Luu");
        luuButton1.setFont(font2);
        luuButton1.setBounds(200,330,80,40);
        luuButton1.setForeground(Color.BLACK);
        luuButton1.setBackground(Color.white);
        luuButton1.setFocusPainted(false);


        themPanel.add(displayPicTF1);
        themPanel.add(name1);
        themPanel.add(displayName1);
        themPanel.add(id1);
        themPanel.add(displayID1);
        themPanel.add(quantity1);
        themPanel.add(displayQuantity1);
        themPanel.add(unit1);
        themPanel.add(displayUnit1);
        themPanel.add(bid_prices1);
        themPanel.add(displayBidPrices1);
        themPanel.add(ask_prices1);
        themPanel.add(displayAskPrices1);
        themPanel.add(luuButton1);

        // create nhap Panel
        JPanel nhapPanel = new JPanel();
        nhapPanel.setLayout(null);
        nhapPanel.setBounds(830,60,350,550);
        nhapPanel.setBackground(new Color(0,220,255));

        JLabel getName = new JLabel("Ten San Pham");
        getName.setFont(font1);
        getName.setForeground(Color.black);
        getName.setBounds(20,20,100,20);

        JTextField insertQuantity = new JTextField();
        insertQuantity.setFont(font1);
        insertQuantity.setBounds(150,20,50,20);

        JLabel getCost = new JLabel("Gia Ban");
        getCost.setFont(font1);
        getCost.setBounds(230,20,70,20);

        LocalDate ld = LocalDate.now();
        Date date = Date.valueOf(ld);

        JLabel time = new JLabel(String.valueOf(date));
        time.setFont(font1);
        time.setBounds(20,50,100,20);

        JButton addButton = new JButton("Them");
        addButton.setFont(font1);
        addButton.setBounds(240,50,100,20);
        addButton.setForeground(Color.BLACK);
        addButton.setBackground(Color.white);
        addButton.setFocusPainted(false);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Ten SP");
        model.addColumn("So Luong");
        model.addColumn("Don Vi");
        model.addColumn("Tong Gia");
        JTable nhapTable = new JTable(model);
        nhapTable.setFont(font1);
        nhapTable.setBounds(25,100,300,400);

        JScrollPane sp = new JScrollPane(nhapTable);
        sp.setBounds(25,100,300,400);

        JLabel tong = new JLabel("Tong: ");
        tong.setFont(font1);
        tong.setForeground(Color.black);
        tong.setBounds(20,510,65,20);

        JLabel tongMua = new JLabel("0");
        tongMua.setFont(font1);
        tongMua.setForeground(Color.black);
        tongMua.setBounds(90,510,150,20);

        JButton muaButton = new JButton("Mua");
        muaButton.setFont(font1);
        muaButton.setForeground(Color.black);
        muaButton.setBounds(270,510,65,25);

        JButton xoaSP = new JButton("Xoa");
        xoaSP.setFont(font1);
        xoaSP.setForeground(Color.black);
        xoaSP.setBounds(190,510,65,25);

        insertQuantity.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = insertQuantity.getText();
                int quantity;
                try
                {
                    quantity = Integer.valueOf(text);
                    getCost.setText(String.valueOf(a.getObject(finalI).getBid_prices() * quantity));
                }
                catch (Exception ex)
                {
                    getCost.setText("0");
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = insertQuantity.getText();
                int quantity;
                try
                {
                    quantity = Integer.valueOf(text);
                    getCost.setText(String.valueOf(a.getObject(finalI).getBid_prices() * quantity));
                }
                catch (Exception ex)
                {
                    getCost.setText("0");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String text = insertQuantity.getText();
                int quantity;
                try
                {
                    quantity = Integer.valueOf(text);
                    getCost.setText(String.valueOf(a.getObject(finalI).getBid_prices() * quantity));
                }
                catch (Exception ex)
                {
                    getCost.setText("0");
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] data = {getName.getText(), insertQuantity.getText(), a.getObject(finalI).getUnit(),  getCost.getText()};
                DefaultTableModel model = (DefaultTableModel) nhapTable.getModel();
                model.addRow(data);
                tongMua.setText(String.valueOf(Integer.valueOf(tongMua.getText()) + Integer.valueOf(getCost.getText())));
                a.getObject(finalI).setQuantity(Integer.valueOf(a.getObject(finalI).getQuantity() + Integer.valueOf(insertQuantity.getText())));

            }
        });

        xoaSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) nhapTable.getModel();
                tongMua.setText(String.valueOf(Integer.valueOf(tongMua.getText()) - Integer.valueOf(String.valueOf(nhapTable.getValueAt(nhapTable.getSelectedRow(), 3)))));
                a.getObject(finalI).setQuantity(Integer.valueOf(a.getObject(finalI).getQuantity() - Integer.valueOf(String.valueOf(nhapTable.getValueAt(nhapTable.getSelectedRow(),1)))));
                model.removeRow(nhapTable.getSelectedRow());

            }
        });


        muaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File(pathFile + "\\Objects.txt");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    for(int i = 0; i < a.getAmountOfObjects(); i++)
                    {
                        writer.write(a.getObject(i).getName() + "\n");
                        writer.write(a.getObject(i).getId() + "\n");
                        writer.write(a.getObject(i).getQuantity() + "\n");
                        writer.write(a.getObject(i).getUnit() + "\n");
                        writer.write(a.getObject(i).getBid_prices() + "\n");
                        writer.write(a.getObject(i).getAsk_prices() + "\n");
                        writer.write(a.getObject(i).getPathPic() + "\n");
                    }
                    writer.close();

                    // tao file nhap Hang
                    DefaultTableModel model = (DefaultTableModel) nhapTable.getModel();
                    for(int i = 0; i < 100; i++)
                    {
                        File file3 = new File(pathNhap + "/" + String.valueOf(date) +"_" + i + ".txt");
                        if(file3.exists() == false)
                        {
                            BufferedWriter writer1 = new BufferedWriter(new FileWriter(file3));
                            writer1.write( date + "\n");
                            for(int j = 0; j < model.getRowCount(); j++)
                            {
                                writer1.write(model.getValueAt(j,0) + "\t");
                                writer1.write(model.getValueAt(j,1) + " ");
                                writer1.write(model.getValueAt(j,2) + "\t");
                                writer1.write(model.getValueAt(j,3) + "\n");
                            }
                            writer1.write("Tong: " + tongMua.getText());
                            writer1.close();
                            break;
                        }
                    }

                    frame.setVisible(false);
                    new Entry();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        nhapPanel.add(time);
        nhapPanel.add(xoaSP);
        nhapPanel.add(muaButton);
        nhapPanel.add(tong);
        nhapPanel.add(tongMua);
        nhapPanel.add(sp);
        nhapPanel.add(addButton);
        nhapPanel.add(insertQuantity);
        nhapPanel.add(getCost);
        nhapPanel.add(getName);

        // create ban Panel
        JPanel banPanel = new JPanel();
        banPanel.setLayout(null);
        banPanel.setBounds(830,60,350,550);
        banPanel.setBackground(new Color(0,220,255));

        JLabel getName1 = new JLabel("Ten San Pham");
        getName1.setFont(font1);
        getName1.setForeground(Color.black);
        getName1.setBounds(20,20,100,20);

        JTextField insertQuantity1 = new JTextField();
        insertQuantity1.setFont(font1);
        insertQuantity1.setBounds(150,20,50,20);

        JLabel getCost1 = new JLabel("Gia Ban");
        getCost1.setFont(font1);
        getCost1.setBounds(230,20,70,20);

        LocalDate ld1 = LocalDate.now();
        Date date1 = Date.valueOf(ld1);

        JLabel time1 = new JLabel(String.valueOf(date1));
        time1.setFont(font1);
        time1.setBounds(20,50,100,20);

        JButton addButton1 = new JButton("Them");
        addButton1.setFont(font1);
        addButton1.setBounds(240,50,100,20);
        addButton1.setForeground(Color.BLACK);
        addButton1.setBackground(Color.white);
        addButton1.setFocusPainted(false);

        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("Ten SP");
        model1.addColumn("So Luong");
        model1.addColumn("Don Vi");
        model1.addColumn("Tong Gia");
        JTable nhapTable1 = new JTable(model1);
        nhapTable1.setFont(font1);
        nhapTable1.setBounds(25,100,300,200);

        JScrollPane sp1 = new JScrollPane(nhapTable1);
        sp1.setBounds(25,100,300,200);

        JTextArea ttKH = new JTextArea();
        ttKH.setFont(font1);
        ttKH.setBackground(Color.white);
        ttKH.setForeground(Color.black);
        ttKH.setBounds(25,320,300,100);

        JTextField ttTien = new JTextField();
        ttTien.setFont(font1);
        ttTien.setBackground(Color.white);
        ttTien.setForeground(Color.black);
        ttTien.setBounds(120,440,200,20);

        JLabel tt = new JLabel("Tra Truoc: ");
        tt.setFont(font1);
        tt.setForeground(Color.black);
        tt.setBounds(20,440,80,20);

        JLabel tong1 = new JLabel("Tong: ");
        tong1.setFont(font1);
        tong1.setForeground(Color.black);
        tong1.setBounds(20,510,65,20);

        JLabel tongBan1 = new JLabel("0");
        tongBan1.setFont(font1);
        tongBan1.setForeground(Color.black);
        tongBan1.setBounds(90,510,150,20);

        JButton muaButton1 = new JButton("Ban");
        muaButton1.setFont(font1);
        muaButton1.setForeground(Color.black);
        muaButton1.setBounds(270,510,65,25);

        JButton xoaSP1 = new JButton("Xoa");
        xoaSP1.setFont(font1);
        xoaSP1.setForeground(Color.black);
        xoaSP1.setBounds(190,510,65,25);

        insertQuantity1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = insertQuantity1.getText();
                int quantity;
                try
                {
                    quantity = Integer.valueOf(text);
                    getCost1.setText(String.valueOf(a.getObject(finalI).getAsk_prices() * quantity));
                }
                catch (Exception ex)
                {
                    getCost1.setText("0");
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = insertQuantity1.getText();
                int quantity;
                try
                {
                    quantity = Integer.valueOf(text);
                    getCost1.setText(String.valueOf(a.getObject(finalI).getAsk_prices() * quantity));
                }
                catch (Exception ex)
                {
                    getCost1.setText("0");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String text = insertQuantity1.getText();
                int quantity;
                try
                {
                    quantity = Integer.valueOf(text);
                    getCost1.setText(String.valueOf(a.getObject(finalI).getAsk_prices() * quantity));
                }
                catch (Exception ex)
                {
                    getCost1.setText("0");
                }
            }
        });

        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] data = {getName1.getText(), insertQuantity1.getText(), a.getObject(finalI).getUnit(), getCost1.getText()};
                DefaultTableModel model = (DefaultTableModel) nhapTable1.getModel();
                model.addRow(data);
                tongBan1.setText(String.valueOf(Integer.valueOf(tongBan1.getText()) + Integer.valueOf(getCost1.getText())));
                a.getObject(finalI).setQuantity(Integer.valueOf(a.getObject(finalI).getQuantity() - Integer.valueOf(insertQuantity1.getText())));

            }
        });

        xoaSP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) nhapTable1.getModel();
                tongBan1.setText(String.valueOf(Integer.valueOf(tongBan1.getText()) - Integer.valueOf(String.valueOf(nhapTable1.getValueAt(nhapTable1.getSelectedRow(), 3)))));
                a.getObject(finalI).setQuantity(Integer.valueOf(a.getObject(finalI).getQuantity() + Integer.valueOf(String.valueOf(nhapTable1.getValueAt(nhapTable1.getSelectedRow(),1)))));
                model.removeRow(nhapTable1.getSelectedRow());
            }
        });


        muaButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File(pathFile + "\\Objects.txt");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    for(int i = 0; i < a.getAmountOfObjects(); i++)
                    {
                        writer.write(a.getObject(i).getName() + "\n");
                        writer.write(a.getObject(i).getId() + "\n");
                        writer.write(a.getObject(i).getQuantity() + "\n");
                        writer.write(a.getObject(i).getUnit() + "\n");
                        writer.write(a.getObject(i).getBid_prices() + "\n");
                        writer.write(a.getObject(i).getAsk_prices() + "\n");
                        writer.write(a.getObject(i).getPathPic() + "\n");
                    }
                    writer.close();

                    // tao file ban Hang
                    DefaultTableModel model = (DefaultTableModel) nhapTable1.getModel();
                    for(int i = 0; i < 100; i++)
                    {
                        File file3 = new File(pathBan + "/" + String.valueOf(date) +"_" + i + ".txt");
                        if(file3.exists() == false)
                        {
                            BufferedWriter writer1 = new BufferedWriter(new FileWriter(file3));
                            writer1.write( date1 + "\n");
                            for(int j = 0; j < model.getRowCount(); j++)
                            {
                                writer1.write(model.getValueAt(j,0) + "\t");
                                writer1.write(model.getValueAt(j,1) + " ");
                                writer1.write(model.getValueAt(j,2) + "\t");
                                writer1.write(model.getValueAt(j,2) + "\n");
                            }
                            writer1.write("Tong: " + tongBan1.getText() + "\n");
                            writer1.write("Tra Truoc: " + ttTien.getText() + "\n");
                            writer1.write(ttKH.getText());
                            writer1.close();

                            if(Integer.valueOf(tongBan1.getText()) > Integer.valueOf(ttTien.getText()))
                            {
                                try{
                                    FileInputStream fis = new FileInputStream(file3.getPath());
                                    FileOutputStream fos = new FileOutputStream(pathNo + "/" + file3.getName());

                                    int c;
                                    while((c = fis.read()) != -1)
                                    {
                                        fos.write(c);
                                    }
                                }catch (Exception ex)
                                {

                                }
                            }

                            break;
                        }
                    }

                    frame.setVisible(false);
                    new Entry();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        banPanel.add(tt);
        banPanel.add(ttTien);
        banPanel.add(ttKH);
        banPanel.add(time1);
        banPanel.add(xoaSP1);
        banPanel.add(muaButton1);
        banPanel.add(tong1);
        banPanel.add(tongBan1);
        banPanel.add(sp1);
        banPanel.add(addButton1);
        banPanel.add(insertQuantity1);
        banPanel.add(getCost1);
        banPanel.add(getName1);


        //create button Them
        JButton themButton = new JButton("Them");
        themButton.setFont(font1);
        themButton.setBackground(new Color(33,146,211));
        themButton.setForeground(Color.WHITE);
        themButton.setFocusPainted(false);
        themButton.setBounds(830,30,100,30);
        ImageIcon imgcThemButton = new ImageIcon(getClass().getClassLoader().getResource("add_icon.png"));
        Image imgThemButton = imgcThemButton.getImage();
        imgThemButton = imgThemButton.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Icon iconThemButton = new ImageIcon(imgThemButton);
        themButton.setIcon(iconThemButton);

        //create button xem
        JButton xemButton = new JButton("Xem");
        xemButton.setFont(font1);
        xemButton.setBackground(new Color(33,146,211));
        xemButton.setForeground(Color.WHITE);
        xemButton.setFocusPainted(false);
        xemButton.setBounds(932,30,90,30);
        ImageIcon imgcXemButton = new ImageIcon(getClass().getClassLoader().getResource("view_icon.png"));
        Image imgXemButton = imgcXemButton.getImage();
        imgXemButton = imgXemButton.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Icon iconXemButton = new ImageIcon(imgXemButton);
        xemButton.setIcon(iconXemButton);

        //create button nhap
        JButton nhapButton = new JButton("Nhap");
        nhapButton.setFont(font1);
        nhapButton.setBackground(new Color(33,146,211));
        nhapButton.setForeground(Color.WHITE);
        nhapButton.setFocusPainted(false);
        nhapButton.setBounds(1024,30,90,30);

        //create button ban
        JButton banButton = new JButton("Ban");
        banButton.setFont(font1);
        banButton.setBackground(new Color(33,146,211));
        banButton.setForeground(Color.WHITE);
        banButton.setFocusPainted(false);
        banButton.setBounds(1116,30,64,30);

        //add Action for viewButton, themButton, nhapButton, banButton, and goods[i]
        xemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xemButton.setBackground(new Color(0,220,255));
                themButton.setBackground(new Color(33,146,211));
                nhapButton.setBackground(new Color(33,146,211));
                banButton.setBackground(new Color(33,146,211));
                viewPanel.setVisible(true);
                themPanel.setVisible(false);
                nhapPanel.setVisible(false);
                banPanel.setVisible(false);
            }
        });

        themButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themButton.setBackground(new Color(0,220,255));
                xemButton.setBackground(new Color(33,146,211));
                nhapButton.setBackground(new Color(33,146,211));
                banButton.setBackground(new Color(33,146,211));
                themPanel.setVisible(true);
                viewPanel.setVisible(false);
                nhapPanel.setVisible(false);
                banPanel.setVisible(false);
            }
        });

        nhapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themButton.setBackground(new Color(33,146,211));
                xemButton.setBackground(new Color(33,146,211));
                nhapButton.setBackground(new Color(0,220,255));
                banButton.setBackground(new Color(33,146,211));
                themPanel.setVisible(false);
                viewPanel.setVisible(false);
                nhapPanel.setVisible(true);
                banPanel.setVisible(false);
            }
        });

        banButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themButton.setBackground(new Color(33,146,211));
                xemButton.setBackground(new Color(33,146,211));
                nhapButton.setBackground(new Color(33,146,211));
                banButton.setBackground(new Color(0,220,255));
                themPanel.setVisible(false);
                viewPanel.setVisible(false);
                nhapPanel.setVisible(false);
                banPanel.setVisible(true);
            }
        });

        for(int i = 0; i < a.getAmountOfObjects(); i++)
        {
            int finalI1 = i;
            goods[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    finalI = finalI1;
                    getName.setText(a.getObject(finalI).getName());
                    getName1.setText(a.getObject(finalI).getName());
                    ImageIcon imgc = new ImageIcon(a.getObject(finalI).getPathPic());
                    Image img = imgc.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
                    pic.setIcon(new ImageIcon(img));
                    displayName.setText(a.getObject(finalI).getName());
                    displayID.setText(a.getObject(finalI).getId());
                    displayQuantity.setText(String.valueOf(a.getObject(finalI).getQuantity()));
                    displayBidPrices.setText(String.valueOf(a.getObject(finalI).getBid_prices()));
                    displayAskPrices.setText(String.valueOf(a.getObject(finalI).getAsk_prices()));
                }
            });
        }


        // action for tranfer picture
        Drag_Drop d = new Drag_Drop(pic);
        pic.setDropTarget(new DropTarget(this,d));

        Drag_Drop d1 = new Drag_Drop(pic1);
        pic1.setDropTarget(new DropTarget(this, d1));


        //action of luu xoa luu1
        luuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // xu ly hinh anh
                try {
                    FileInputStream in = new FileInputStream(new File(d.getPicPath()));
                    FileOutputStream out = new FileOutputStream(new File(pathIG + "\\" + d.getPicName()));
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }

                    in.close();
                    out.close();

                    File file = new File(a.getObject(finalI).getPathPic());
                    file.delete();
                    a.getObject(finalI).setPathPic(pathIG + "\\" + d.picName);
                }
                catch (Exception ex)
                {

                }

                //luu lai file text
                try {
                    File file = new File(pathFile + "\\Objects.txt");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    for(int i = 0; i < a.getAmountOfObjects(); i++)
                    {
                        if(i == finalI)
                        {
                            writer.write(displayName.getText() + "\n");
                            writer.write(displayID.getText() + "\n");
                            writer.write(displayQuantity.getText() + "\n");
                            writer.write(displayUnit.getText() + "\n");
                            writer.write(displayBidPrices.getText() + "\n");
                            writer.write(displayAskPrices.getText() + "\n");
                            writer.write(a.getObject(finalI).getPathPic() + "\n");
                        }
                        else
                        {
                            writer.write(a.getObject(i).getName() + "\n");
                            writer.write(a.getObject(i).getId() + "\n");
                            writer.write(a.getObject(i).getQuantity() + "\n");
                            writer.write(a.getObject(i).getBid_prices() + "\n");
                            writer.write(a.getObject(i).getAsk_prices() + "\n");
                            writer.write(a.getObject(i).getPathPic() + "\n");
                        }
                    }
                    writer.close();

                    frame.setVisible(false);
                    new Entry();

                } catch (Exception ex) {

                }
            }
        });

        xoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File(pathFile + "\\Objects.txt");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    for(int i = 0; i < a.getAmountOfObjects(); i++)
                    {
                        if(i != finalI)
                        {
                            writer.write(displayName.getText() + "\n");
                            writer.write(displayID.getText() + "\n");
                            writer.write(displayQuantity.getText() + "\n");
                            writer.write(displayUnit.getText() + "\n");
                            writer.write(displayBidPrices.getText() + "\n");
                            writer.write(displayAskPrices.getText() + "\n");
                            writer.write(a.getObject(finalI).getPathPic() + "\n");
                        }
                    }
                    writer.close();

                    File file3 = new File(a.getObject(finalI).getPathPic());
                    file3.delete();

                    frame.setVisible(false);
                    new Entry();
                } catch (Exception ex) {

                }
            }
        });

        luuButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileInputStream in = new FileInputStream(new File(d1.getPicPath()));
                    FileOutputStream out = new FileOutputStream(new File(pathIG + "\\" + d1.getPicName()));
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }

                    in.close();
                    out.close();


                } catch (Exception ex) {

                }

                //luu lai file text
                File file = new File(pathFile + "\\Objects.txt");
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                    writer.write(displayName1.getText() + "\n");
                    writer.write(displayID1.getText() + "\n");
                    writer.write(displayQuantity1.getText() + "\n");
                    writer.write(displayUnit1.getText() + "\n");
                    writer.write(displayBidPrices1.getText() + "\n");
                    writer.write(displayAskPrices1.getText() + "\n");
                    writer.write( pathIG + "\\"+d1.getPicName() + "\n");

                    writer.close();

                    frame.setVisible(false);
                    new Entry();

                } catch (Exception ex) {

                }
            }
        });

        //create nhap hang va ban hang panel

        khoPanel.add(themPanel);
        khoPanel.add(viewPanel);
        khoPanel.add(nhapPanel);
        nhapPanel.setVisible(false);
        khoPanel.add(banPanel);
        banPanel.setVisible(false);
        khoPanel.add(xemButton);
        khoPanel.add(themButton);
        khoPanel.add(nhapButton);
        khoPanel.add(banButton);
        khoPanel.add(tkLB);
        khoPanel.add(tkLB2);
        khoPanel.add(tkTF);
        khoPanel.add(tkTF2);
        khoPanel.add(goodsPanel);
    }


    public void createXuatPanel()
    {
        File file = new File(pathBan.toString());
        String[] str1;
        if(file.list() == null)
        {
            str1 = new String[0];
        }
        else
        {
            str1 = file.list();
        }
        String[] str = str1;

        for(int i = 0; i < str.length; i++)
        {
            for(int j = i+1; j < str.length; j++)
            {
                if(str[i].compareTo(str[j]) > 0 || str[i].length() > str[j].length())
                {
                    String temp = str[i];
                    str[i] = str[j];
                    str[j] = temp;
                }
            }
        }

        JPanel expPanel = new JPanel();
        expPanel.setLayout(null);
        expPanel.setBounds(10,60,800,500);
        expPanel.setBackground(new Color(0,220,255));

        JPanel expPanel1 = new JPanel();
        expPanel1.setLayout(null);
        expPanel1.setBounds(820,60,360,500);
        expPanel1.setBackground(new Color(0,220,255));

        Font font1 = new Font("TimesNewRoman", Font.BOLD, 13);

        JTextArea textArea = new JTextArea();
        textArea.setFont(font1);
        textArea.setBounds(10,10,340,400);

        JButton xoaButton = new JButton("Xoa");
        xoaButton.setFont(font1);
        xoaButton.setBounds(20,420,60,20);
        xoaButton.setForeground(Color.BLACK);
        xoaButton.setBackground(Color.white);
        xoaButton.setFocusPainted(false);

        exp = new JButton[str.length];
        //create button of goods
        for(int i = 0; i < str.length; i++) {
            exp[i] = new JButton();
            exp[i].setFont(font1);
            exp[i].setForeground(Color.WHITE);
            exp[i].setLayout(null);
            exp[i].setBorder(new Border() {
                @Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                    g.setColor(new Color(68, 187, 139));
                    g.fillRoundRect(x, y, width, height, 20, 20);
                }

                @Override
                public Insets getBorderInsets(Component c) {
                    return new Insets(10, 10, 10, 10);
                }

                @Override
                public boolean isBorderOpaque() {
                    return true;
                }
            });
            exp[i].setSize(120, 30);
            exp[i].setBackground(new Color(0, 220, 255));

            JLabel labelName = new JLabel(str[i]);

            labelName.setFont(font1);
            labelName.setForeground(Color.WHITE);
            labelName.setBounds(10, 10, 100, 15);

            exp[i].setLocation((i % 6) * 130 + 10, (i / 6) * 40 + 10);
            exp[i].add(labelName);

            int finalI1 = i;
            exp[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        expIndex = finalI1;
                        File file = new File(pathBan + "/" + str[expIndex]);
                        Scanner sc = new Scanner(file);
                        String text = "";
                        while(sc.hasNext())
                        {
                            text = text + (sc.nextLine() + "\n");
                        }
                        sc.close();
                        textArea.setText(text);
                    }catch (Exception ex)
                    {

                    }
                }
            });

            expPanel.add(exp[i]);
        }


        //create tkTF
        JLabel tkLB = new JLabel("Tim Kiem Theo Ten");
        tkLB.setBounds(10,30,120,20);
        JTextField tkTF = new JTextField();
        tkTF.setFont(font1);
        tkTF.setForeground(new Color(82,85,87));

        tkTF.setBounds(130,30,300,20);tkTF.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            int location = 0;
            String text = tkTF.getText();
            if(text.trim().length() == 0)
            {
                for(int i = 0; i < str.length; i++)
                {
                    exp[i].setLocation((i % 6) * 130 + 10, (i / 6) * 70 + 10);
                    exp[i].setVisible(true);
                }
            }
            else
            {
                for(int i = 0; i < str.length; i++)
                {
                    if(str[i].indexOf(text) != -1)
                    {
                        exp[i].setLocation((location % 6) * 130 + 10, (location / 6) * 70 + 10);
                        exp[i].setVisible(true);
                        location++;
                    }
                    else
                    {
                        exp[i].setVisible(false);
                    }
                }
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            int location = 0;
            String text = tkTF.getText();
            if(text.trim().length() == 0)
            {
                for(int i = 0; i < str.length; i++)
                {
                    exp[i].setLocation((i % 6) * 130 + 10, (i / 6) * 70 + 10);
                    exp[i].setVisible(true);
                }
            }
            else
            {
                for(int i = 0; i < str.length; i++)
                {
                    if(str[i].indexOf(text) != -1)
                    {
                        exp[i].setLocation((location % 6) * 130 + 10, (location / 6) * 70 + 10);
                        exp[i].setVisible(true);
                        location++;
                    }
                    else
                    {
                        exp[i].setVisible(false);
                    }
                }
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            int location = 0;
            String text = tkTF.getText();
            if(text.trim().length() == 0)
            {
                for(int i = 0; i < str.length; i++)
                {
                    exp[i].setLocation((i % 6) * 130 + 10, (i / 6) * 70 + 10);
                    exp[i].setVisible(true);
                }
            }
            else
            {
                for(int i = 0; i < str.length; i++)
                {
                    if(str[i].indexOf(text) != -1)
                    {
                        exp[i].setLocation((location % 6) * 130 + 10, (location / 6) * 70 + 10);
                        exp[i].setVisible(true);
                        location++;
                    }
                    else
                    {
                        exp[i].setVisible(false);
                    }
                }
            }
        }
    });

        xoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File(pathBan + "/" + str[expIndex]);
                file.delete();

                frame.setVisible(false);
                try {
                    new Entry();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        expPanel1.add(textArea);
        expPanel1.add(xoaButton);

        xuatPanel.add(expPanel1);
        xuatPanel.add(expPanel);
        xuatPanel.add(tkLB);
        xuatPanel.add(tkTF);
    }

    public void createNhapPanel(){
        File file = new File(pathNhap.toString());
        String[] str1;
        if(file.list() == null)
        {
            str1 = new String[0];
        }
        else {
            str1 = file.list();
        }
        String[] str = str1;
        for(int i = 0; i < str.length; i++)
        {
            for(int j = i+1; j < str.length; j++)
            {
                if(str[i].compareTo(str[j]) > 0 || str[i].length() > str[j].length())
                {
                    String temp = str[i];
                    str[i] = str[j];
                    str[j] = temp;
                }
            }
        }

        JPanel impPanel = new JPanel();
        impPanel.setLayout(null);
        impPanel.setBounds(10,60,800,500);
        impPanel.setBackground(new Color(0,220,255));

        JPanel impPanel1 = new JPanel();
        impPanel1.setLayout(null);
        impPanel1.setBounds(820,60,360,500);
        impPanel1.setBackground(new Color(0,220,255));

        Font font1 = new Font("TimesNewRoman", Font.BOLD, 13);

        JTextArea textArea = new JTextArea();
        textArea.setFont(font1);
        textArea.setBounds(10,10,340,400);

        JButton xoaButton = new JButton("Xoa");
        xoaButton.setFont(font1);
        xoaButton.setBounds(20,420,60,20);
        xoaButton.setForeground(Color.BLACK);
        xoaButton.setBackground(Color.white);
        xoaButton.setFocusPainted(false);

        imp = new JButton[str.length];
        //create button of goods
        for(int i = 0; i < str.length; i++) {
            imp[i] = new JButton();
            imp[i].setFont(font1);
            imp[i].setForeground(Color.WHITE);
            imp[i].setLayout(null);
            imp[i].setBorder(new Border() {
                @Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                    g.setColor(new Color(68, 187, 139));
                    g.fillRoundRect(x, y, width, height, 20, 20);
                }

                @Override
                public Insets getBorderInsets(Component c) {
                    return new Insets(10, 10, 10, 10);
                }

                @Override
                public boolean isBorderOpaque() {
                    return true;
                }
            });
            imp[i].setSize(120, 30);
            imp[i].setBackground(new Color(0, 220, 255));

            JLabel labelName = new JLabel(str[i]);

            labelName.setFont(font1);
            labelName.setForeground(Color.WHITE);
            labelName.setBounds(10, 10, 100, 15);

            imp[i].setLocation((i % 6) * 130 + 10, (i / 6) * 40 + 10);
            imp[i].add(labelName);

            int finalI1 = i;
            imp[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        impIndex = finalI1;
                        File file = new File(pathNhap + "/" + str[impIndex]);
                        Scanner sc = new Scanner(file);
                        String text = "";
                        while(sc.hasNext())
                        {
                            text = text + (sc.nextLine() + "\n");
                        }
                        sc.close();
                        textArea.setText(text);
                    }catch (Exception ex)
                    {

                    }
                }
            });

            impPanel.add(imp[i]);
        }


        //create tkTF
        JLabel tkLB = new JLabel("Tim Kiem Theo Ten");
        tkLB.setBounds(10,30,120,20);
        JTextField tkTF = new JTextField();
        tkTF.setFont(font1);
        tkTF.setForeground(new Color(82,85,87));

        tkTF.setBounds(130,30,300,20);tkTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                int location = 0;
                String text = tkTF.getText();
                if(text.trim().length() == 0)
                {
                    for(int i = 0; i < str.length; i++)
                    {
                        exp[i].setLocation((i % 6) * 130 + 10, (i / 6) * 70 + 10);
                        exp[i].setVisible(true);
                    }
                }
                else
                {
                    for(int i = 0; i < str.length; i++)
                    {
                        if(str[i].indexOf(text) != -1)
                        {
                            exp[i].setLocation((location % 6) * 130 + 10, (location / 6) * 70 + 10);
                            exp[i].setVisible(true);
                            location++;
                        }
                        else
                        {
                            exp[i].setVisible(false);
                        }
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                int location = 0;
                String text = tkTF.getText();
                if(text.trim().length() == 0)
                {
                    for(int i = 0; i < str.length; i++)
                    {
                        exp[i].setLocation((i % 6) * 130 + 10, (i / 6) * 70 + 10);
                        exp[i].setVisible(true);
                    }
                }
                else
                {
                    for(int i = 0; i < str.length; i++)
                    {
                        if(str[i].indexOf(text) != -1)
                        {
                            exp[i].setLocation((location % 6) * 130 + 10, (location / 6) * 70 + 10);
                            exp[i].setVisible(true);
                            location++;
                        }
                        else
                        {
                            exp[i].setVisible(false);
                        }
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                int location = 0;
                String text = tkTF.getText();
                if(text.trim().length() == 0)
                {
                    for(int i = 0; i < str.length; i++)
                    {
                        exp[i].setLocation((i % 6) * 130 + 10, (i / 6) * 70 + 10);
                        exp[i].setVisible(true);
                    }
                }
                else
                {
                    for(int i = 0; i < str.length; i++)
                    {
                        if(str[i].indexOf(text) != -1)
                        {
                            exp[i].setLocation((location % 6) * 130 + 10, (location / 6) * 70 + 10);
                            exp[i].setVisible(true);
                            location++;
                        }
                        else
                        {
                            exp[i].setVisible(false);
                        }
                    }
                }
            }
        });

        xoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File(pathNhap + "/" + str[impIndex]);
                file.delete();

                frame.setVisible(false);
                try {
                    new Entry();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        impPanel1.add(textArea);
        impPanel1.add(xoaButton);

        nhapPanel.add(impPanel1);
        nhapPanel.add(impPanel);
        nhapPanel.add(tkLB);
        nhapPanel.add(tkTF);
    }

    public void createNoPanel()
    {
        File file = new File(pathNo.toString());
        String[] str1;
        if(file.list() == null)
        {
            str1 = new String[0];
        }
        else
        {
            str1 = file.list();
        }
        String[] str = str1;

        for(int i = 0; i < str.length; i++)
        {
            for(int j = i+1; j < str.length; j++)
            {
                if(str[i].compareTo(str[j]) > 0 || str[i].length() > str[j].length())
                {
                    String temp = str[i];
                    str[i] = str[j];
                    str[j] = temp;
                }
            }
        }

        JPanel debtPanel = new JPanel();
        debtPanel.setLayout(null);
        debtPanel.setBounds(10,60,800,500);
        debtPanel.setBackground(new Color(0,220,255));

        JPanel debtPanel1 = new JPanel();
        debtPanel1.setLayout(null);
        debtPanel1.setBounds(820,60,360,500);
        debtPanel1.setBackground(new Color(0,220,255));

        Font font1 = new Font("TimesNewRoman", Font.BOLD, 13);

        JTextArea textArea = new JTextArea();
        textArea.setFont(font1);
        textArea.setBounds(10,10,340,400);

        JButton xoaButton = new JButton("Xoa");
        xoaButton.setFont(font1);
        xoaButton.setBounds(20,420,60,20);
        xoaButton.setForeground(Color.BLACK);
        xoaButton.setBackground(Color.white);
        xoaButton.setFocusPainted(false);

        debt = new JButton[str.length];
        //create button of goods
        for(int i = 0; i < str.length; i++) {
            debt[i] = new JButton();
            debt[i].setFont(font1);
            debt[i].setForeground(Color.WHITE);
            debt[i].setLayout(null);
            debt[i].setBorder(new Border() {
                @Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                    g.setColor(new Color(68, 187, 139));
                    g.fillRoundRect(x, y, width, height, 20, 20);
                }

                @Override
                public Insets getBorderInsets(Component c) {
                    return new Insets(10, 10, 10, 10);
                }

                @Override
                public boolean isBorderOpaque() {
                    return true;
                }
            });
            debt[i].setSize(120, 30);
            debt[i].setBackground(new Color(0, 220, 255));

            JLabel labelName = new JLabel(str[i]);

            labelName.setFont(font1);
            labelName.setForeground(Color.WHITE);
            labelName.setBounds(10, 10, 100, 15);

            debt[i].setLocation((i % 6) * 130 + 10, (i / 6) * 40 + 10);
            debt[i].add(labelName);

            int finalI1 = i;
            debt[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        debtIndex = finalI1;
                        File file = new File(pathNo + "/" + str[debtIndex]);
                        Scanner sc = new Scanner(file);
                        String text = "";
                        while(sc.hasNext())
                        {
                            text = text + (sc.nextLine() + "\n");
                        }
                        sc.close();
                        textArea.setText(text);
                    }catch (Exception ex)
                    {

                    }
                }
            });

            debtPanel.add(debt[i]);
        }


        //create tkTF
        JLabel tkLB = new JLabel("Tim Kiem Theo Ten");
        tkLB.setBounds(10,30,120,20);
        JTextField tkTF = new JTextField();
        tkTF.setFont(font1);
        tkTF.setForeground(new Color(82,85,87));

        tkTF.setBounds(130,30,300,20);tkTF.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            int location = 0;
            String text = tkTF.getText();
            if(text.trim().length() == 0)
            {
                for(int i = 0; i < str.length; i++)
                {
                    exp[i].setLocation((i % 6) * 130 + 10, (i / 6) * 70 + 10);
                    exp[i].setVisible(true);
                }
            }
            else
            {
                for(int i = 0; i < str.length; i++)
                {
                    if(str[i].indexOf(text) != -1)
                    {
                        exp[i].setLocation((location % 6) * 130 + 10, (location / 6) * 70 + 10);
                        exp[i].setVisible(true);
                        location++;
                    }
                    else
                    {
                        exp[i].setVisible(false);
                    }
                }
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            int location = 0;
            String text = tkTF.getText();
            if(text.trim().length() == 0)
            {
                for(int i = 0; i < str.length; i++)
                {
                    exp[i].setLocation((i % 6) * 130 + 10, (i / 6) * 70 + 10);
                    exp[i].setVisible(true);
                }
            }
            else
            {
                for(int i = 0; i < str.length; i++)
                {
                    if(str[i].indexOf(text) != -1)
                    {
                        exp[i].setLocation((location % 6) * 130 + 10, (location / 6) * 70 + 10);
                        exp[i].setVisible(true);
                        location++;
                    }
                    else
                    {
                        exp[i].setVisible(false);
                    }
                }
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            int location = 0;
            String text = tkTF.getText();
            if(text.trim().length() == 0)
            {
                for(int i = 0; i < str.length; i++)
                {
                    exp[i].setLocation((i % 6) * 130 + 10, (i / 6) * 70 + 10);
                    exp[i].setVisible(true);
                }
            }
            else
            {
                for(int i = 0; i < str.length; i++)
                {
                    if(str[i].indexOf(text) != -1)
                    {
                        exp[i].setLocation((location % 6) * 130 + 10, (location / 6) * 70 + 10);
                        exp[i].setVisible(true);
                        location++;
                    }
                    else
                    {
                        exp[i].setVisible(false);
                    }
                }
            }
        }
    });

        xoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File(pathNo + "/" + str[debtIndex]);
                file.delete();

                frame.setVisible(false);
                try {
                    new Entry();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        debtPanel1.add(textArea);
        debtPanel1.add(xoaButton);

        noPanel.add(debtPanel1);
        noPanel.add(debtPanel);
        noPanel.add(tkLB);
        noPanel.add(tkTF);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public static void main(String[] args) throws IOException, URISyntaxException {
        Entry a = new Entry();
    }
}