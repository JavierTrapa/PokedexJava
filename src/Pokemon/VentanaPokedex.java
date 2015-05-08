package Pokemon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorgecisneros
 */
public class VentanaPokedex extends javax.swing.JFrame {
    private int contador=0;
    private BufferedImage buffer;
    private Image imagenPokemons;
    int x=0;
    int y=0;
    private int ancho=200;
    private int alto=200;
    private int anchoSprite=96;
    private int altoSprite=96;
    
    //conectamos la base de datos
    private Statement estado;
    private ResultSet resultadoConsulta;
    private Connection conexion;
    
    HashMap <String,Pokemon>listaPokemons=new HashMap();
    /**
     * Creates new form VentanaPokedex
     */
    
    private void dibujaElPokemonQueEstaEnLaPosicion(int posicion){
        int fila=posicion/31;
        int columna=posicion%31;
        Graphics2D g2=(Graphics2D) buffer.getGraphics();
        g2.setColor(Color.black);
        g2.fillRect(0, 0, alto, ancho);
        g2.drawImage(imagenPokemons,
                x,
                y,
                ancho,
                alto,
                anchoSprite*columna,
                altoSprite*fila,
                anchoSprite*columna+anchoSprite,
                altoSprite*fila+altoSprite,
                null);
        repaint();
        escribeDatos();
    }
    /*private String evolucionPrevia(Pokemon p){
        String evolucion;
        Pokemon aux;
        if (p.evolution_chain_id==0){
            evolucion="Nadie";
        }else{
            aux.id=p.evolution_chain_id;
            evolucion=aux.nombre;
        }
        return evolucion;
    }*/
    
    private String parametroEvolucion(Pokemon p){
        if(p.evolution_parameter.length()>2){
            return p.evolution_parameter;
        }else{
            return ("nivel "+p.evolution_parameter);
        }
    }
    
    private void escribeDatos(){
        Pokemon p=listaPokemons.get(String.valueOf(contador+1));
        if(p!=null){
            jLabel2.setText(p.nombre+"     "+p.id);
            jTextArea1.setText("Nombre: "+p.nombre+'\n'+"Numero: "+p.id+'\n'+
                    "Evoluciona de: "+/*evolucionPrevia*/'\n'+
                    "Evoluciona con: "+parametroEvolucion(p)+'\n'+
                    "Altura: "+p.height+"dm"+'\n'+
                    "Peso: "+p.weight+"hg"+'\n'+
                    "Especie: "+p.species+'\n'+
                    "Habitat: "+p.habitat+'\n'+
                    "% de captura: "+p.capture_rate+"%"+'\n'+
                    "Experiencia base: "+p.base_experience+'\n'+
                    "Felicidad base: "+p.base_happiness+'\n');
        }else{
            jLabel2.setText("No Hay Datos");
            jTextArea1.setText("No Hay Datos");
        }
    }
    
    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
        Graphics2D g2 =(Graphics2D) jPanel1.getGraphics();
        g2.drawImage(buffer, 0, 0,ancho,alto,null);
    }
    
    public VentanaPokedex() {
        initComponents();
        try {
            imagenPokemons=ImageIO.read(getClass().getResource("black-white.png"));
        } catch (IOException ex) {
            Logger.getLogger(VentanaPokedex.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.getContentPane().setBackground(Color.red);
        buffer=(BufferedImage)  jPanel1.createImage(ancho, alto);
        Graphics2D g2=buffer.createGraphics();
        
        
        
        //conectarme a la base de datos
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection("jdbc:mysql://127.0.0.1/test", "root", "");
            estado=conexion.createStatement();
            resultadoConsulta=estado.executeQuery("Select * from pokemon");
            //cargo el resultado de mi query en un hashmap
            while(resultadoConsulta.next()){
                Pokemon p = new Pokemon();
                p.nombre=resultadoConsulta.getString(2);
                p.generation_id=resultadoConsulta.getInt(5);
                p.evolution_chain_id=resultadoConsulta.getInt(6);
                p.species=resultadoConsulta.getString(12);
                p.id=resultadoConsulta.getInt(1);
                p.height=resultadoConsulta.getInt(10);
                p.weight=resultadoConsulta.getInt(11);
                p.habitat=resultadoConsulta.getString(15);
                p.capture_rate=resultadoConsulta.getInt(17);
                p.base_experience=resultadoConsulta.getInt(18);
                p.base_happiness=resultadoConsulta.getInt(19);
                
                listaPokemons.put(resultadoConsulta.getString(1), p);
            }
        }catch(Exception e){
            
        }
        dibujaElPokemonQueEstaEnLaPosicion(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonAnterior = new javax.swing.JButton();
        jButtonSiguiente = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        jButtonAnterior.setBackground(new java.awt.Color(204, 204, 0));
        jButtonAnterior.setText("Anterior");
        jButtonAnterior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonAnteriorMousePressed(evt);
            }
        });

        jButtonSiguiente.setBackground(new java.awt.Color(204, 204, 0));
        jButtonSiguiente.setText("Siguiente");
        jButtonSiguiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonSiguienteMousePressed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Busqueda por nombre");

        jButton1.setBackground(new java.awt.Color(204, 204, 0));
        jButton1.setText("Buscar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        jLabel3.setText("Busqueda por numero de id");

        jButton2.setBackground(new java.awt.Color(204, 204, 0));
        jButton2.setText("Buscar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButtonAnterior)
                                .addGap(52, 52, 52)
                                .addComponent(jButtonSiguiente))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAnteriorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAnteriorMousePressed
        contador--;
        if(contador<0){contador=0;}
        dibujaElPokemonQueEstaEnLaPosicion(contador);
    }//GEN-LAST:event_jButtonAnteriorMousePressed

    private void jButtonSiguienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSiguienteMousePressed
        contador++;
        if(contador>516){contador=516;}
        dibujaElPokemonQueEstaEnLaPosicion(contador);
    }//GEN-LAST:event_jButtonSiguienteMousePressed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        if(jTextField1.getText()!=""){
            //if(jTextField1.getText()==p.nombre){
                
            //}
        }else{
            jLabel1.setText("No se han introducido datos");
        }
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        if(jTextField1.getText()!=""){
            //if(jTextField1.getText()==p.nombre){
                
            //}
        }else{
            jLabel1.setText("No se han introducido datos");
        }
    }//GEN-LAST:event_jButton2MousePressed

    
            
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPokedex().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAnterior;
    private javax.swing.JButton jButtonSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
