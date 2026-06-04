import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/javabanco";
        String user = "root";
        String senha = "";
        int opcao;

        try {
            Connection conexao = DriverManager.getConnection(url,user,senha);
            System.out.println("Conectado!");

            do{
                System.out.println("""
                        
                        ===MENU===
                        1 - Inserir
                        2 - Listar
                        3 - Atualizar
                        4 - Deletar
                        0 - Sair
                        """);

                System.out.println("Digite a opção: ");
                opcao = sc.nextInt();

                sc.nextLine();
                switch (opcao){
                    case 1:
                        System.out.println("CREATE\n");
                        System.out.println("Digite o nome:");
                        String nome = sc.nextLine();
                        System.out.println("Digite o email:");
                        String email = sc.nextLine();

                        String insert = "insert into usuario (nome, email) values (?,?)";
                        PreparedStatement psInsert = conexao.prepareStatement(insert);
                        psInsert.setString(1,nome);
                        psInsert.setString(2,email);
                        psInsert.executeUpdate();
                        System.out.println("Usuario inserido!");
                        break;
                    case 2:
                        System.out.println("READ\n");
                        String select = "select * from usuario";
                        Statement stat = conexao.createStatement();
                        ResultSet rs = stat.executeQuery(select);
                        System.out.println("LISTA");
                        while(rs.next()){
                            System.out.printf("%d - %s - %s%n",
                                    rs.getInt("id"),
                                    rs.getString("nome"),
                                    rs.getString("email")
                            );
                        }
                        break;
                    case 3:
                        System.out.println("UPDATE\n");
                        System.out.println("Digite o id para atualizar:");
                        int idUpdate = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Novo Nome: ");
                        String nomeUpdate = sc.nextLine();
                        System.out.print("Novo Email: ");
                        String emailUpdate = sc.nextLine();

                        String update = "update usuario set nome = ?, email = ? where id = ?";
                        PreparedStatement psUpdate = conexao.prepareStatement(update);
                        psUpdate.setString(1, nomeUpdate);
                        psUpdate.setString(2, emailUpdate);
                        psUpdate.setInt(3,idUpdate);
                        psUpdate.executeUpdate();
                        System.out.println("Atualizado");

                        break;
                    case 4:
                        System.out.println("DELETE\n");
                        System.out.println("DIgite um id para deletar:");
                        int idelete = sc.nextInt();
                        sc.nextLine();

                        String delete = "delete from usuario where id = ?";
                        PreparedStatement psDelete = conexao.prepareStatement(delete);
                        psDelete.setInt(1,idelete);
                        psDelete.executeUpdate();
                        System.out.println("Deletado!");
                        break;
                    case 0:
                        System.out.println("Programa Finalizado");
                    default:
                        System.out.println("opção inválida");
                }

            }while (opcao != 0);
            conexao.close();


        }catch(Exception e){
            e.printStackTrace();
        }

        sc.close();
    }
}
