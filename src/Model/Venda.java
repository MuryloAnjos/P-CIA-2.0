
package Model;

import java.time.LocalDateTime;

public class Venda {
    
    private int id;
    private LocalDateTime data;
    private String met_pagamento;
    private double valor_total;
    private double troco;
    private String cli_cpf;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getMet_pagamento() {
        return met_pagamento;
    }

    public void setMet_pagamento(String met_pagamento) {
        this.met_pagamento = met_pagamento;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    public String getCli_cpf() {
        return cli_cpf;
    }

    public void setCli_cpf(String cli_cpf) {
        this.cli_cpf = cli_cpf;
    }
    
    public void abrir_venda(){
        
    }
    
    
}
