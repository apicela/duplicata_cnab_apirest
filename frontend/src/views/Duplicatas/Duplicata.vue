<template>
  <div>
    <div class="card">
      <div class="card-header">
        <h4>
          duplicata
          <RouterLink to="/duplicatas/create" class="btn btn-primary float-end">
            Criar duplicata
          </RouterLink>
        </h4>
      </div>
      <div class="card-body">
<table class="table table-bordered">
<thead>
          <tr>
            <th>ID</th>
            <th>Razão Social</th>
            <th>ID Empresa</th>
            <th>Transferências</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(duplicata, index) in this.duplicatas" :key "index">
            <td>{{ duplicata.duplicata_id }}</td>

          </tr>
        </tbody>
      </table>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import axios from 'axios';


export default {
  name: 'duplicatas',
  data(){
    return {
      duplicatas:[]
    }
  },
  mounted(){
    console.log('mounted')
    this.getAllDuplicatas();
  },
  methods: {
    getAllDuplicatas(){
      axios.get('http://localhost:8080/duplicatas').then( res => {
        this.duplicatas = res.data
      }).catch(err => {
        console.error('Error fetching duplicatas', err);
      });
    }  
  },
}
</script>
