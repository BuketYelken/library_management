/*

@Author : BuketYelken

 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewUsers extends JFrame{
    private JPanel viewUsersPanel;
    private JTable viewUsersTable;
    private JButton removeUserButton;
    private JButton searchUserButton;

    ViewUsers(){
        super("View Users");
        this.setContentPane(this.viewUsersPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);;
        this.pack();

        DefaultTableModel userDataModel = (DefaultTableModel) viewUsersTable.getModel();
        Database db = new Database();
        viewUsersTable.setModel(db.viewUsers(userDataModel)); //kullanıcıları veri tabanından viewuser fonksiyonu ile dataModeli gönderip verimi return ediyor
        removeUserButton.addActionListener(new ActionListener() {  //butona basıp basmadığını kontrol ediyor
            @Override
            public void actionPerformed(ActionEvent e) {
                String userIdentify= JOptionPane.showInputDialog("Please type identify number: ");
                db.removeUser(userIdentify); // kullanıcı silindi
                userDataModel.setRowCount(0); // row sıfırlandı
                userDataModel.setColumnCount(0); // column sıfırlandı
                viewUsersTable.setModel(db.viewUsers(userDataModel)); // yenilenen veri tekrar çekildi
            }
        });
        searchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userIdentify = JOptionPane.showInputDialog("Please type identify number: ");
                String[] data = db.searchByIdentify(userIdentify); // veri string[] tipi geldiği için string[] tipinde değişkene atandı
                if(data.length > 0){ // eğer veri varsa
                    JOptionPane.showMessageDialog(viewUsersPanel,"Firstname: " + data[0] + "\n" + "Lastname :" + data[1] + "\n" + "Password : " + data[2] + "\n" + "ID Number :" + data[3] + "\n" + "Role :" + data[4] );
                }else{
                    JOptionPane.showMessageDialog(viewUsersPanel,"User not found");
                }

            }
        });
    }
}
