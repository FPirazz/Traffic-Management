<script>
import axios from "axios"
import sweetalert from "sweetalert"
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      loginName: '',
      loginSurname: '',
      loginPassword: '',
    }
  },
  computed: {
    ...mapGetters("user", ["name", "surname"]),
  },
  methods: {
    checkIfRegistered() {
      axios
          .get('/users/check?name=' + this.loginName + "&surname=" + this.loginSurname + "&password=" + this.loginPassword)
          .then((res) => {
            if(res.data) {
              sweetalert({
                text: "User logged in succesfully",
                icon: "success"
              })
              axios.get('/users')
                  .then((res) => {
                    var user = this.getUserByCreds(res.data._embedded.userList, this.loginName, this.loginSurname, this.loginPassword)
                    this.$store.commit("user/loginName", this.loginName)
                    this.$store.commit("user/loginSurname", this.loginSurname)
                    this.$store.commit("user/loginRole", user[0].role)
                    this.$router.push("/")
                  });
            } else {
              sweetalert({
                text: "Login failed. User not registered.",
                icon: "error"
              });
            }
          })
          .catch((err) => {
            sweetalert({
              text: "Login failed. Network error.",
              icon: "error"
            });
            console.log("Errore di tipo: " + err)
          });
    },
    getUserByCreds(data, name, surname, password) {
      return data.filter(
          function(data){
            return data.name == name && data.surname == surname && data.password == password
          }
      );
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
            <input type="text" class="form-control" v-model="loginName">
          </div>
          <div class="form-group">
            <label>User surname</label>
            <input type="text" class="form-control" v-model="loginSurname">
          </div>
          <div class="form-group">
            <label>User Password</label>
            <input type="password" class="form-control" v-model="loginPassword">
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