<template>
  <div class="container">
    <div class="card" style="margin-top: 3.5%">
      <div class="card-header">
        <h4>
          Gerenciador de duplicatas
          <button @click="attachFile" class="btn btn-primary float-end">
            Criar duplicata
          </button>
        </h4>
      </div>
      <div class="card-body">
        <table class="table table-bordered">
          <thead align="center">
            <tr>
              <th class="tituloTable">ID</th>
              <th class="tituloTable">Razão Social</th>
              <th class="tituloTable">Identificador da Empresa</th>
              <th class="tituloTable">Transferências</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(duplicata, index) in this.duplicatas" :key="index">
              <td>{{ duplicata.duplicata_id }}</td>
              <td>{{ duplicata.razaoSocial }}</td>
              <td>{{ duplicata.identificadorEmpresa }}</td>
              <td align="center">
                <button
                  type="button"
                  class="btn btn-primary"
                  @click="openTransfersModel(duplicata.transacoes)"
                  style="
                    background-color: rgb(255, 255, 255);
                    border-color: rgb(141, 159, 209);
                  "
                >
                  📝
                </button>
              </td>
            </tr>
          </tbody>
        </table>

        <!-- Modal de transferencia -->
        <div class="modal" tabindex="-1" role="dialog" ref="transfersModel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Transferencias</h5>
                <button
                  type="button"
                  class="close"
                  data-dismiss="modal"
                  aria-label="Close"
                  @click="closeTransfersModel"
                >
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <ul v-for="(transacao, index) in modalData" :key="index">
                  <li>{{ index + 1 }}:</li>
                  <p style="margin-left: 6%">
                    Tipo de Transação: {{ transacao.tipoTransacao }}
                  </p>
                  <p style="margin-left: 6%">
                    Valor da Transação: {{ transacao.valorTransacao }}
                  </p>
                  <p style="margin-left: 6%">
                    Conta de Origem: {{ transacao.contaOrigem }}
                  </p>
                  <p style="margin-left: 6%">
                    Conta de Destino: {{ transacao.contaDestino }}
                  </p>
                  <br />
                </ul>
              </div>
            </div>
          </div>
        </div>

        <!-- Modal de sucesso -->
        <div class="modal" tabindex="-1" role="dialog" ref="sucessModel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Detalhes da Duplicata</h5>
                <button
                  type="button"
                  class="close"
                  data-dismiss="modal"
                  aria-label="Close"
                  @click="closeSucessModel"
                >
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <p>Arquivo CNAB enviado e processado com sucesso!</p>
                <br />
                <h6>Transferências realizadas:</h6>

                <ul
                  v-if="
                    modalData &&
                    modalData.duplicata &&
                    modalData.duplicata.transacoes
                  "
                  v-for="(transacao, index) in modalData.duplicata.transacoes"
                  :key="index"
                >
                  <li>{{ index + 1 }}:</li>
                  <p style="margin-left: 6%">
                    Tipo de Transação: {{ transacao.tipoTransacao }}
                  </p>
                  <p style="margin-left: 6%">
                    Valor da Transação: {{ transacao.valorTransacao }}
                  </p>
                  <p style="margin-left: 6%">
                    Conta de Origem: {{ transacao.contaOrigem }}
                  </p>
                  <p style="margin-left: 6%">
                    Conta de Destino: {{ transacao.contaDestino }}
                  </p>
                  <br />
                </ul>
              </div>
            </div>
          </div>
        </div>

     <!-- Modal de error -->
     <div class="modal" tabindex="-1" role="dialog" ref="errorModel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Erro ao inserir duplicata!</h5>
                <button
                  type="button"
                  class="close"
                  data-dismiss="modal"
                  aria-label="Close"
                  @click="closeErrorModel"
                >
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <p>status: {{ modalData.status }}</p>
                <p>mensagem: {{ modalData.message }}</p>
                <p>lista de erros:</p>
                <ul>
                <p v-for="(erro, index) in modalData.errors" :key="index">
                  {{ erro }}
                </p>
              </ul>
                
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "duplicatas",
  data() {
    return {
      duplicatas: [],
      modalData: [],
    };
  },
  mounted() {
    console.log("mounted");
    this.getAllDuplicatas();
  },
  methods: {
    getAllDuplicatas() {
      axios
        .get("http://localhost:8080/duplicatas")
        .then((res) => {
          this.duplicatas = res.data;
        })
        .catch((err) => {
          console.error("Error fetching duplicatas", err);
        });
    },
    attachFile() {
      const input = document.createElement("input");
      input.type = "file";
      input.accept = ".pdf,.txt"; // extensoes
      input.onchange = (e) => {
        const file = e.target.files[0];
        console.log("Arquivo selecionado: ", file);
        let formData = new FormData();
        formData.append("file", file);

        axios
          .post("http://localhost:8080/duplicatas", formData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          })
          .then((response) => {
            this.openSucessModel(response.data);
            this.getAllDuplicatas();
            console.log(response.data);
          })
          .catch((error) => {
            console.log('modal data: ', error.response.data);
            this.openErrorModel(error.response.data);
          });
      };
      input.click();
    },
    openTransfersModel(transacoes) {
      this.modalData = transacoes;
      this.$refs.transfersModel.style.display = "block";
    },
    closeTransfersModel() {
      this.$refs.transfersModel.style.display = "none";
    },
    openSucessModel(duplicata) {
      this.modalData = duplicata;
      this.$refs.sucessModel.style.display = "block";
    },
    closeSucessModel() {
      this.$refs.sucessModel.style.display = "none";
    },
    openErrorModel(transacoes) {
      this.modalData = transacoes;
      this.$refs.errorModel.style.display = "block";
    },
    closeErrorModel() {
      this.$refs.errorModel.style.display = "none";
    },
  },
};
</script>

<style>
th.tituloTable {
  background-color: #e6e6e6;
}
</style>
