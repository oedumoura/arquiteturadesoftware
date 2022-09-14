import lombok.Data;

@Data
public class Cliente {

    private String cpf;
    private String nome;
    private List<Conta> contas;


}
