<template>
  <div class="about">
    <iframe
      width="100%"
      height="800px"
      :src="iframeSrc"
      frameborder="0"
    ></iframe>
  </div>
</template>

<script>
export default {
  data() {
    return {
      iframeSrc: "http://localhost:8080/swagger-ui/index.html",
    };
  },
};
</script>

<style>

</style>
