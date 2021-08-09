package Formulario;

import claseConectar.conectar;
import java.sql.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class IngresoProductos extends javax.swing.JInternalFrame {

    DefaultTableModel model;

    /**
     * Creates new form IngresoProductos
     */
    public IngresoProductos() {
        initComponents();
        this.setLocation(500, 50);
        eti();
        txtrecomendacion.setEditable(false);
        bloquear();
        cargar("");
//         cargar1("");
    }

    void bloquear() {
        jccat.setEnabled(false);
        txtdes.setEnabled(false);
        txtorden.setEnabled(false);
        txtpre.setEnabled(false);
        txtcantidad.setEnabled(false);
        txtun.setEnabled(false);
        jccat.setEnabled(false);
        txtstock.setEnabled(false);
        txtpreme.setEnabled(false);
        btnguardar.setEnabled(false);
        btnnuevo.setEnabled(true);
        btncancelar.setEnabled(false);
        btnactualizar.setEnabled(false);

    }

    void limpiar() {
        jccat.setSelectedIndex(0);
        txtdes.setText("");
        txtpre.setText("");
        txtstock.setText("");
        txtorden.setText("");
        jccat.setSelectedItem("");
        txtcantidad.setText("");
        txtun.setText("");

    }

    void desbloquear() {
        jccat.setEnabled(true);
        txtdes.setEnabled(true);
//    txtorden.setEnabled(true);
        txtpre.setEnabled(true);
        txtpreme.setEnabled(true);
        txtcantidad.setEnabled(true);
        txtun.setEnabled(true);
        jccat.setEnabled(true);
        txtstock.setEnabled(true);

        btnguardar.setEnabled(true);
        btnnuevo.setEnabled(false);
        btncancelar.setEnabled(true);
    }

    void cargar(String valor) {
        try {
            String[] titulos = {"Orden", "Descripcion", "Categoria", "Unidad Medida", "Cantidad", "Precio1", "Precio2", "Stock"};
            String[] registros = new String[8];
            model = new DefaultTableModel(null, titulos);

            String cons = "select * from productoma WHERE CONCAT (orden,descripcion,cantidad,un,cantidad,precio,precio2,stock) LIKE '%" + valor + "%'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while (rs.next()) {
                registros[0] = rs.getString(1);
                registros[1] = rs.getString(2);
                registros[2] = rs.getString(3);
                registros[3] = rs.getString(4);
                registros[4] = rs.getString(5);
                registros[5] = rs.getString(6);
                registros[6] = rs.getString(7);
                registros[7] = rs.getString(8);

                model.addRow(registros);
            }
            tbproductos.setModel(model);
            tbproductos.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbproductos.getColumnModel().getColumn(1).setPreferredWidth(270);
            tbproductos.getColumnModel().getColumn(2).setPreferredWidth(150);
            tbproductos.getColumnModel().getColumn(3).setPreferredWidth(150);
            tbproductos.getColumnModel().getColumn(4).setPreferredWidth(100);
            tbproductos.getColumnModel().getColumn(5).setPreferredWidth(100);
            tbproductos.getColumnModel().getColumn(6).setPreferredWidth(100);
            tbproductos.getColumnModel().getColumn(7).setPreferredWidth(100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
//    void cargar1(String valor) {
//        try{
//            String [] titulos={"Orden","Descripcion","Codigo","Unidad","Pack","Precio","Stock"};
//            String [] registros= new String[7];
//            model=new DefaultTableModel(null,titulos);
//            
//            String cons="select * from productome WHERE CONCAT (orden,descripcion,codigo,un,pack,precio,stock) LIKE '%"+valor+"%'";
//            Statement st= cn.createStatement();
//            ResultSet rs = st.executeQuery(cons);
//            while(rs.next()){
//                registros[0]=rs.getString(1);
//                registros[1]=rs.getString(2);
//                registros[2]=rs.getString(3);
//                registros[3]=rs.getString(4);
//                registros[4]=rs.getString(5);
//                registros[5]=rs.getString(6);
//                registros[6]=rs.getString(7);
//                
//                model.addRow(registros);      
//                }
//            tbproductos.setModel(model);
//            tbproductos.getColumnModel().getColumn(0).setPreferredWidth(100);
//            tbproductos.getColumnModel().getColumn(1).setPreferredWidth(200);
//            tbproductos.getColumnModel().getColumn(2).setPreferredWidth(100);
//            }catch(Exception e){
//                System.out.println(e.getMessage());
//                 }
//     
//    }

    void BuscarProductoEditar(String cod) {

        try {

            String orden = "", desc = "", codi = "", un = "", cantidad = "", prec = "", prec2 = "", stock = "";
            String cons = "select * from productoma WHERE orden='" + orden + "'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while (rs.next()) {
                orden = rs.getString(1);
                desc = rs.getString(2);
                codi = rs.getString(3);
                un = rs.getString(4);
                cantidad = rs.getString(5);
                prec = rs.getString(6);
                prec2 = rs.getString(7);
                stock = rs.getString(8);

            }
            txtorden.setText(orden);
            txtdes.setText(desc);
            jccat.setSelectedItem(codi);
            txtun.setText(un);
            txtcantidad.setText(cantidad);
            txtpre.setText(prec);
            txtpreme.setText(prec2);
            txtstock.setText(stock);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    void codigos() {

        int j;
        int cont = 1;
        String num = "";
        String c = "";
        String SQL = "select max(orden) from productoma";
        // String SQL="select count(*) from factura";
        //String SQL="SELECT MAX(cod_emp) AS cod_emp FROM empleado";
        //String SQL="SELECT @@identity AS ID";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }

            if (c == null) {
                txtorden.setText("OP0001");
            } else {
                char r1 = c.charAt(2);
                char r2 = c.charAt(3);
                char r3 = c.charAt(4);
                char r4 = c.charAt(5);
                String r = "";
                r = "" + r1 + r2 + r3 + r4;
                j = Integer.parseInt(r);
                GenerarCodigos_Op gen = new GenerarCodigos_Op();
                gen.generar(j);
                txtorden.setText(gen.serie());
                

            }

        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnactualizar = new javax.swing.JMenuItem();
        mneliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtdes = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtpre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtstock = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtorden = new javax.swing.JTextField();
        txtun = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtpreme = new javax.swing.JTextField();
        jccat = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbproductos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtrecomendacion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        LblNombreP = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        mnactualizar.setText("Modificar datos seleccionados");
        mnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnactualizarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnactualizar);

        mneliminar.setText(" Eliminar datos seleccionados");
        mneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mneliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mneliminar);

        setClosable(true);
        setIconifiable(true);
        setTitle("Registro de productos");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de Producto"));

        jLabel1.setText("Categoria:");

        jLabel2.setText("Descripcion:");

        txtdes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdesActionPerformed(evt);
            }
        });

        jLabel3.setText("Precio mesa $ :");

        txtpre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpreActionPerformed(evt);
            }
        });

        jLabel5.setText("Stock:");

        txtstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstockActionPerformed(evt);
            }
        });

        jLabel6.setText("Orden:");

        txtorden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtordenActionPerformed(evt);
            }
        });

        txtun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtunActionPerformed(evt);
            }
        });

        jLabel7.setText("Unidad:");

        jLabel8.setText("Cantidad:");

        txtcantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcantidadActionPerformed(evt);
            }
        });

        jLabel9.setText("Precio delivery $:");

        txtpreme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpremeActionPerformed(evt);
            }
        });

        jccat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Comida", "Bebida", "Postre" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtdes)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtpre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtorden, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtcantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                        .addComponent(jccat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtpreme, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtun, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(txtstock))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtorden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtdes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel7))
                    .addComponent(jccat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9)
                            .addComponent(txtpreme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtpre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/set-1/add_folder.gif"))); // NOI18N
        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/set-1/Copia de save.gif"))); // NOI18N
        btnguardar.setText("Grabar");
        btnguardar.setToolTipText("");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/set-1/close.gif"))); // NOI18N
        btnactualizar.setText("Actualizar");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/set-1/Copia de break.gif"))); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/set-1/x.gif"))); // NOI18N
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnnuevo)
                .addGap(18, 18, 18)
                .addComponent(btnguardar)
                .addGap(18, 18, 18)
                .addComponent(btnactualizar)
                .addGap(18, 18, 18)
                .addComponent(btncancelar)
                .addGap(18, 18, 18)
                .addComponent(btnsalir)
                .addGap(26, 26, 26))
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbproductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbproductos.setComponentPopupMenu(jPopupMenu1);
        tbproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbproductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbproductos);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Buscar:");

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/set-1/search.gif"))); // NOI18N
        jButton1.setText("Mostrar Todo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtrecomendacion.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtrecomendacion.setForeground(new java.awt.Color(204, 0, 0));
        txtrecomendacion.setText("Recomendacion : Expresar precios con \"punto\" y no utilizar \"comas\" para un correcto calculo");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtrecomendacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtrecomendacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jLabel10.setText("Sesión iniciada como :");

        LblNombreP.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblNombreP.setForeground(new java.awt.Color(255, 51, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jButton1))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LblNombreP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
// TODO add your handling code here:
    this.dispose();
}//GEN-LAST:event_btnsalirActionPerformed

private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
// TODO add your handling code here:
    desbloquear();
    limpiar();
    txtorden.requestFocus();
    codigos();
}//GEN-LAST:event_btnnuevoActionPerformed

private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
// TODO add your handling code here:
    bloquear();
}//GEN-LAST:event_btncancelarActionPerformed

private void txtdesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdesActionPerformed
// TODO add your handling code here:
    txtdes.transferFocus();
}//GEN-LAST:event_txtdesActionPerformed

public void eti(){
String eti;
eti = Principal.LblNombreP.getText();
LblNombreP.setText(eti);

}



private void txtpreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpreActionPerformed

    txtpre.transferFocus();
}//GEN-LAST:event_txtpreActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    cargar("");
}//GEN-LAST:event_jButton1ActionPerformed

private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
// TODO add your handling code here:
    cargar(txtbuscar.getText());
}//GEN-LAST:event_txtbuscarKeyReleased

private void mneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mneliminarActionPerformed
// TODO add your handling code here:
    int filasel = tbproductos.getSelectedRow();
    try {
        if (filasel == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione alguna fila");
        } else {

            String orden = (String) tbproductos.getValueAt(filasel, 0);
            String opcion[] = {"Eliminar", "Cancelar"};

            int eleccion = JOptionPane.showOptionDialog(this, "Esta seguro de eliminar el registro para siempre?. ", "ATENCION!!!", 0, 0, null, opcion, evt);
            if (eleccion == JOptionPane.YES_OPTION) {
                String eliminarSQL = "DELETE FROM productoma WHERE orden = '" + orden + "'";
                try {
                    PreparedStatement pst = cn.prepareStatement(eliminarSQL);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Registro eliminado con exito.");
                    cargar("");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fila y vuelva a intentarlo.");
            }
        }
    } catch (Exception e) {
    }
}//GEN-LAST:event_mneliminarActionPerformed

//public void preciome(){
//String orden,des,cod,un,pack,pre,stock;
//            String sql="";
//            orden=txtorden.getText();
//            des=txtdes.getText();
//            cod=txtcod.getText();
//            un=txtun.getText();
//            pack=txtpack.getText();
//            pre=txtpreme.getText();
//            stock=txtstock.getText();
//            sql="INSERT INTO productome (orden,descripcion,codigo,un,pack,precio,Stock) VALUES (?,?,?,?,?,?,?)";
//        try {
//            PreparedStatement pst  = cn.prepareStatement(sql);
//             pst.setString(1, orden);
//             pst.setString(2, des);
//            pst.setString(3, cod);
//            pst.setString(4, un);
//            pst.setString(5, pack);            
//            pst.setString(6, pre);
//            pst.setString(7, stock);
//            int n=pst.executeUpdate();
//            if(n>0){
//            JOptionPane.showMessageDialog(null, "Registro Guardado con Exito");
//            bloquear();
//            }
//            
//            cargar("");
//        } catch (SQLException ex) {
//            Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//}

private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
// TODO add your handling code here:
    String orden, des, cod, un, cantidad, pre, pre2, stock;
    String sql = "";
    orden = txtorden.getText();
    des = txtdes.getText();
    cod = jccat.getSelectedItem().toString();
    un = txtun.getText();
    cantidad = txtcantidad.getText();
    pre = txtpre.getText();
    pre2 = txtpreme.getText();
    stock = txtstock.getText();
    sql = "INSERT INTO productoma (orden,descripcion,categoria,un,cantidad,precio,precio2,Stock) VALUES (?,?,?,?,?,?,?,?)";
    try {
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, orden);
        pst.setString(2, des);
        pst.setString(3, cod);
        pst.setString(4, un);
        pst.setString(5, cantidad);
        pst.setString(6, pre);
        pst.setString(7, pre2);
        pst.setString(8, stock);
        int n = pst.executeUpdate();
//            preciome();
        if (n > 0) {
            JOptionPane.showMessageDialog(null, "Registro Guardado con Exito");
            bloquear();
        }
        cargar("");
    } catch (SQLException ex) {
        Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_btnguardarActionPerformed

private void mnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnactualizarActionPerformed
// TODO add your handling code here:

    desbloquear();
    btnactualizar.setEnabled(true);
    int filamodificar = tbproductos.getSelectedRow();
    if (filamodificar >= 0) {
        txtorden.setText(tbproductos.getValueAt(filamodificar, 0).toString());
        txtdes.setText(tbproductos.getValueAt(filamodificar, 1).toString());
        jccat.setSelectedItem(tbproductos.getValueAt(filamodificar, 2).toString());
        txtun.setText(tbproductos.getValueAt(filamodificar, 3).toString());
        txtcantidad.setText(tbproductos.getValueAt(filamodificar, 4).toString());
        txtpre.setText(tbproductos.getValueAt(filamodificar, 5).toString());
        txtpreme.setText(tbproductos.getValueAt(filamodificar, 6).toString());
        txtstock.setText(tbproductos.getValueAt(filamodificar, 7).toString());

    }

}//GEN-LAST:event_mnactualizarActionPerformed

private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
// TODO add your handling code here:
    String sql = "UPDATE productoma SET descripcion= '" + txtdes.getText() + "', categoria = '" + jccat.getSelectedItem()+ "', un= '" + txtun.getText() + "', cantidad='" + txtcantidad.getText() + "', precio = '" + txtpre.getText() + "', precio2 = '" + txtpreme.getText() + "', Stock = '" + txtstock.getText() + "' WHERE orden = '" + txtorden.getText() + "'";
    try {
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Actualizado");
        cargar("");
        bloquear();
        limpiar();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}//GEN-LAST:event_btnactualizarActionPerformed

private void txtstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstockActionPerformed
    txtstock.transferFocus();
}//GEN-LAST:event_txtstockActionPerformed

    private void txtordenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtordenActionPerformed
        txtorden.transferFocus();
    }//GEN-LAST:event_txtordenActionPerformed

    private void txtunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtunActionPerformed
        txtun.transferFocus();
    }//GEN-LAST:event_txtunActionPerformed

    private void txtcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidadActionPerformed
        txtcantidad.transferFocus();
    }//GEN-LAST:event_txtcantidadActionPerformed

    private void txtpremeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpremeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpremeActionPerformed

    private void tbproductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbproductosMouseClicked
        int filamodificar = tbproductos.getSelectedRow();
        if (filamodificar >= 0) {
            txtorden.setText(tbproductos.getValueAt(filamodificar, 0).toString());
            txtdes.setText(tbproductos.getValueAt(filamodificar, 1).toString());
            jccat.setSelectedItem(tbproductos.getValueAt(filamodificar, 2).toString());
            txtun.setText(tbproductos.getValueAt(filamodificar, 3).toString());
            txtcantidad.setText(tbproductos.getValueAt(filamodificar, 4).toString());
            txtpre.setText(tbproductos.getValueAt(filamodificar, 5).toString());
            txtpreme.setText(tbproductos.getValueAt(filamodificar, 6).toString());
            txtstock.setText(tbproductos.getValueAt(filamodificar, 7).toString());

        }
    }//GEN-LAST:event_tbproductosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblNombreP;
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jccat;
    private javax.swing.JMenuItem mnactualizar;
    private javax.swing.JMenuItem mneliminar;
    private javax.swing.JTable tbproductos;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtdes;
    private javax.swing.JTextField txtorden;
    private javax.swing.JTextField txtpre;
    private javax.swing.JTextField txtpreme;
    private javax.swing.JTextField txtrecomendacion;
    private javax.swing.JTextField txtstock;
    private javax.swing.JTextField txtun;
    // End of variables declaration//GEN-END:variables
   conectar cc = new conectar();
    Connection cn = cc.conexion();
}
