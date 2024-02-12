import java.util.Scanner;

class Carrefour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final double HORA_APERTURA = 9.0;
        final double HORA_CIERRE = 21.0;
        final double MINUTOS = 1.0 / 60.0;
        double atendiendo = HORA_APERTURA;
        boolean trabajando = true;
        int minutosSinFila = 0;
        int totalClientesAtendidos = 0;
        int totalItemsVendidos = 0;

        int fila = 0;
        final double PROBABILIDAD_LLEGADA_CLIENTE = 0.6;
        final int CANTIDAD_CAJEROS = 4;
        int [] caja = new int [CANTIDAD_CAJEROS];
        final int ITEMS_MAXIMO_CLIENTE = 15;
        final int ITEMS_MINIMO_CLIENTE = 5;
        boolean clienteCajaExtra = false;
        int itemsCajaExtra = 0;

        do {
            cleanScreen();
            atendiendo += MINUTOS;
            int horas = (int) atendiendo;
            int minutos = (int) ((atendiendo - horas) * 60);
            
            System.out.println("Hora actual: " + horas + ":" + minutos);
            imprimeLinea();
            trabajando = atendiendo < HORA_CIERRE;
            if (fila == 0){
                minutosSinFila++;
            }
            if (Math.random() < PROBABILIDAD_LLEGADA_CLIENTE) {
                fila = fila + 1;
            }

            if (clienteCajaExtra){
                if (itemsCajaExtra > 0) {
                    itemsCajaExtra--;
                    if (itemsCajaExtra == 0){
                        System.out.println("La caja extra está vacía.");
                        clienteCajaExtra = false;
                    } else {
                        System.out.println("Un cliente se dirige a la caja extra con [" +itemsCajaExtra+ "] items");
                    }
                }
            }

            if (fila > 15 && !clienteCajaExtra) {
                for (int cajaExtra = 0; cajaExtra < CANTIDAD_CAJEROS; cajaExtra++) {
                    if (caja[cajaExtra] <= 0 && !clienteCajaExtra) {
                        caja[cajaExtra] = (int) (Math.random() * (ITEMS_MAXIMO_CLIENTE - ITEMS_MINIMO_CLIENTE))  + ITEMS_MINIMO_CLIENTE;
                        fila--;
                        totalClientesAtendidos++;
                        totalItemsVendidos += caja[cajaExtra];
                        clienteCajaExtra = true;
                        itemsCajaExtra = caja[cajaExtra];
                    }
                }
            }

            for (int atender = 0; atender < CANTIDAD_CAJEROS; atender++){
                if (caja[atender] <= 0 && fila > 0){
                    fila --;
                    totalClientesAtendidos++;
                    int itemsVenta = (int) (Math.random() * (ITEMS_MAXIMO_CLIENTE - ITEMS_MINIMO_CLIENTE)) + ITEMS_MINIMO_CLIENTE;
                    totalItemsVendidos += itemsVenta;
                    caja[atender] = itemsVenta;
                } else if (caja[atender] > 0){
                    caja[atender]--;
                }

                if (caja[atender] > 0){
                    System.out.println("Caja " + (atender + 1) + " atiende al cliente y le quedan [" + caja[atender] + "] items.");
                } else {
                    System.out.println("Caja " + (atender + 1) + " se encuentra vacia.");
                }
            }
            imprimeLinea();
            System.out.println("Cantidad de clientes en fila: " + fila);
            imprimeLinea();
            scanner.nextLine();
        } while (trabajando);

        System.out.println("RESUMEN");
        imprimeLineaResumen();
        System.out.println("Cantidad de minutos en los que no hubo nadie en la fila: " + minutosSinFila);
        System.out.println("Cantidad de clientes que permanecieron en fila a la hora de cierre: " + fila);
        System.out.println("Al finalizar el día se atendieron " + totalClientesAtendidos + " clientes.");
        System.out.println("En total se vendieron " + totalItemsVendidos + " items.");
        imprimeLineaResumen();
        scanner.close();
    }
    static void imprimeLineaResumen() {
        System.out.println("===========".repeat(7));
    }
    static void imprimeLinea(){
        System.out.println("---------+".repeat(5));
    }

    static void cleanScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}