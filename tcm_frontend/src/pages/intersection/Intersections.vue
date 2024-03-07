<template>
  <div class="container">
    <div class="row">
      <RouterLink class="nav-link" :to="{ name: 'IntersectionPage' }">
        <div class="card-body text-center">
          <h5 class="card-title">Intersection Test 1</h5>
          <p class="card-text">Address - Address Test 1</p>
        </div>
      </RouterLink>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  computed: {
  },
  methods: {

  },
  async mounted() {

  },
};
</script>

<style scoped></style>