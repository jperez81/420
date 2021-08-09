package Formulario;

public class GenerarCodigos_Op {

     public int dato;
    public int cont = 1;
    public String num = "";

  public void generar(int dato) {
        this.dato = dato;
         
           if((this.dato>=999) || (this.dato<9999)) 
           {
               int can=cont+this.dato;
               num = "OP" + can; 
           }
           if((this.dato>=99) || (this.dato<999))
           {
               int can=cont+this.dato;
               num = "OP0"  + can; 
           }
           if((this.dato>=9) && (this.dato<99)) 
           {
                int can=cont+this.dato;
               num = "OP00"  + can; 
           }
           if((this.dato>=1) && (this.dato<9)) 
           {
               int can=cont+this.dato;
               num = "OP000" + can; 
           }
           if(this.dato==0)
           {
              int can=cont+this.dato;
               num = "OP000" + can; 
           }
    }

    public String serie() {
        return this.num;
    }

}
