import java.util.Scanner;

public class BigVigenere {
    int[] key;
    char[][] alphabet;

    public BigVigenere() {
        alphabet = new char[][]{{'a','b','c','d','e','f','g','h'},
                                {'i','j','k','l','m','n','ñ','o'},
                                {'p','q','r','s','t','u','v','w'},
                                {'x','y','z','A','B','C','D','E'},
                                {'F','G','H','I','J','K','L','M'},
                                {'N','Ñ','O','P','Q','R','S','T'},
                                {'U','V','W','X','Y','Z','0','1'},
                                {'2','3','4','5','6','7','8','9'},
        };
        System.out.println("Ingrese la clave:");
        Scanner sc = new Scanner(System.in);
        String nkey = sc.nextLine();
        key = new int[nkey.length()];
        for (int x = 0; x < nkey.length(); ++x) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (nkey.charAt(x) == alphabet[i][j]) {
                        key[x] = (i * 8) + j;
                        break;
                    }
                }
            }
        }
    } // Fin del constructor

    public String Encryptar(String message) {
        char[] encrypted = new char[message.length()];
        int[] posicion = new int[message.length()];

        for (int i = 0; i < message.length(); ++i) {
            for (int j = 0; j < 8; ++j) {
                for (int k = 0; k < 8; ++k) {
                    if (message.charAt(i) == alphabet[j][k]) {
                        posicion[i] = ((j * 8) + k + key[i % key.length]) % 64;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < message.length(); ++i){
            int pos = posicion[i] % 64;
            encrypted[i] = OptimalSearch(pos);
        } // Final funcion in to char
        return new String(encrypted);
    } // Fin metodo

    public String Decryptar(String Encrypted) {
        char[] decrypted = new char[Encrypted.length()];
        int[] posicion = new int[Encrypted.length()];

        for (int i = 0; i < Encrypted.length(); ++i) {
            for (int j = 0; j < 8; ++j) {
                for (int k = 0; k < 8; ++k) {
                    if (Encrypted.charAt(i) == alphabet[j][k]) {
                        posicion[i] = ((j * 8) + k - key[i % key.length] + 64) % 64;
                    }
                }
            }
        }
        for (int i = 0; i < Encrypted.length(); ++i){
            int pos = posicion[i] % 64;
            decrypted[i] = OptimalSearch(pos);
        }
        return new String(decrypted);
    } // Fin metodo

    public void reEncrypt() {
        System.out.println("Ingrese el mensaje encriptado:");
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        String decriptado = Decryptar(message);
        BigVigenere bigVigenere = new BigVigenere();
        String encrypted = bigVigenere.Encryptar(decriptado);
        System.out.println("Encrypted message: " + encrypted);
    } // Fin metodo

    public char Search(int position){
        char digito = ' ';
        for (int j = 0; j < 8; ++j) {
            for (int k = 0; k < 8; ++k) {
                if (j * 8 + k == position) {
                    digito = alphabet[j][k];
                }
            }
        }
        return digito;
    } // Fin metodo

    public char OptimalSearch(int position){
        int j, k;
        j = position / 8;
        k = position % 8;
        return alphabet[j][k];
    } // Fin metodo

    public static void main(String[] args) {
        BigVigenere vigenere = new BigVigenere();

        // Metodo Encryptar
        System.out.println("Ingrese el mensaje que quiere encriptar: ");
        Scanner sc = new Scanner(System.in);
        String mensaje = sc.nextLine();
        String encryptado = vigenere.Encryptar(mensaje);
        System.out.println("Mensaje encriptado: " + encryptado);

        // Metodo reEncrypt
        vigenere.reEncrypt();

        // Metodo Decryptar
        String decrypted = vigenere.Decryptar(encryptado);
        System.out.println("Mensaje decrypt: " + decrypted);

        // Metodo Search
        char digit = vigenere.Search(15);
        System.out.println("El digito:12 "+digit);

        // Metodo OptimalSearch
        char digit1 = vigenere.Search(34);
        System.out.println("El digito: "+digit1);
    }
} // Fin de la clase