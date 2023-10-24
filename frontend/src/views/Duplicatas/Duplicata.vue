<template>
  <div class="container">
    <div class="card" style="margin-top:3.5%;">
      <div class="card-header">
        <h4>
          Gerenciador de duplicatas
          <button @click="openModal" class="btn btn-primary float-end">Criar duplicata</button>

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
                  @click="openModal(duplicata.transacoes)"
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

        <!-- Modal -->
        <div class="modal" tabindex="-1" role="dialog" ref="myModal">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Detalhes da Transação</h5>
                <button
                  type="button"
                  class="close"
                  data-dismiss="modal"
                  aria-label="Close"
                  @click="closeModal"
                >
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <ul v-for="(transacao, index) in modalData" :key="index">
                  <li>Tipo de Transação: {{ transacao.tipoTransacao }}</li>
                  <li>Valor da Transação: {{ transacao.valorTransacao }}</li>
                  <li>Conta de Origem: {{ transacao.contaOrigem }}</li>
                  <li>Conta de Destino: {{ transacao.contaDestino }}</li>
                  <br />
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
    openModal(transacoes) {
      this.modalData = transacoes;
      this.$refs.myModal.style.display = "block";
    },
    closeModal() {
      this.$refs.myModal.style.display = "none";
    },  
      async uploadFile() {
      const formData = new FormData();
      formData.append('file', this.file);
      try {
        const response = await axios.post('http://localhost:8080/uploadCNAB', formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        });
        this.modalData = response.data;
        this.$refs.myModal.style.display = 'block';
      } catch (error) {
        console.error('Error uploading file', error);
      }
    },
  },
};
</script>

<style>
th.tituloTable {
  background-color: #e6e6e6;
}
</style>
