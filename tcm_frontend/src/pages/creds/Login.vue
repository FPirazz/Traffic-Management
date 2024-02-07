<script>
import axios from "axios"
import sweetalert from "sweetalert"
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      name: '',
      surname: '',
      password: '',
    }
  },
  computed: {
    ...mapGetters("user", ["name", "surname"]),
  },
  methods: {
    checkIfRegistered() {
      const user = {
        email: this.email,
        surname: this.surname,
        password: this.password,
      };

      axios
          .get('http://localhost:8080/users/check?name=' + this.name + "&surname=" + this.surname + "&password=" + this.password)
          .then((res) => {
            sweetalert({
              text: "User logged in succesfully",
              icon: "success"
            })
            this.$store.commit("user/login", this.name, this.surname)
            // console.log(this.email)
            this.$router.push("/")

          })
          .catch((err) => {
            sweetalert({
              text: "Login failed. User not registered.",
              icon: "error"
            });
            console.log("Errore di tipo: " + err)
          });
    }
  }
}
</script>

<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center">
        <h3 class="pt-3">Login Form</h3>
      </div>
    </div>
    <div class="row">
      <!--Utilizzo bootstrap per creare il form-->
      <div class="col-3"></div>
      <div class="col-6">
        <form>
          <div class="form-group">
            <label>User name</label>
            <input type="text" class="form-control" v-model="name">
          </div>
          <div class="form-group">
            <label>User surname</label>
            <input type="text" class="form-control" v-model="surname">
          </div>
          <div class="form-group">
            <label>User Password</label>
            <input type="password" class="form-control" v-model="password">
          </div>
          <br>
          <div class="text-center">
            <button type="button" class="btn btn-primary" @click="checkIfRegistered">Login</button>
          </div>
        </form>
      </div>
      <div class="col-3"></div>
    </div>
  </div>
</template>

<style scoped></style>