<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
</script>

<template>
  <header>
    <div class="wrapper">
      <nav class="navbar navbar-expand-lg navbar-dark"  style="background-color: #292b2c;">
  <div class="container-fluid">
    <RouterLink class="navbar-brand" to="#">HOME DEVELOPER</RouterLink>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
        <li class="nav-item">
      <a class="nav-link" href="https://github.com/apicela" target="_blank" rel="noopener noreferrer">
        <img src="https://cdn.icon-icons.com/icons2/2351/PNG/512/logo_github_icon_143196.png" alt="Github" style="height: 20px; width: 20px;"/>
      </a>
    </li>
        <!-- <li class="nav-item">
          <RouterLink class="nav-link active" to="/">Inicio</RouterLink>
        </li> -->
        <li class="nav-item">
          <RouterLink class="nav-link" to="/duplicatas">Duplicatas</RouterLink>
        </li>
        <li class="nav-item">
          <RouterLink class="nav-link" to="/swagger">Swagger OpenAI</RouterLink>
        </li>

      </ul>
     
    </div>
  </div>
</nav>
      <nav>

      </nav>
    </div>
  </header>

  <RouterView />
</template>

<style scoped>

</style>
