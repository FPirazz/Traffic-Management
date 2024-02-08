<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center">
        <h3 class="pt-3">Register Form</h3>
      </div>
    </div>
    <div class="row">
      <div class="col-3"></div>
      <div class="col-6">
        <form>
          <div class="form-group">
            <label>User First Name</label>
            <input type="text" class="form-control" v-model="user.name">
          </div>
          <div class="form-group">
            <label>User Last Name</label>
            <input type="text" class="form-control" v-model="user.surname">
          </div>
          <br>
          <div>Role:</div>
          <div class="form-check">
            <input class="form-check-input" type="radio" id="staff" value="Driver" v-model="user.role">
            <label class="form-check-label" for="staff">Driver</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" id="user" value="Operator" v-model="user.role">
            <label class="form-check-label" for="user">Operator</label>
          </div>
          <br>
          <div class="form-group">
            <label>Password</label>
            <input type="password" class="form-control" v-model="user.password">
          </div>
          <br>
          <div class="text-center">
            <button type="button" class="btn btn-primary" @click="addUserToDB">Register</button>
          </div>
        </form>
      </div>
      <div class="col-3"></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import sweetalert from "sweetalert";

export default {
  data() {
    return {
      user: {
        name: "",
        surname: "",
        password: "",
        role: "",
      },
    };
  },
  methods: {
    addUserToDB() {
      axios
          .post("http://127.0.0.1:8080/users", this.user, {
            headers: {
              "Content-Type" : "application/json"
            }
          })
          .then(() => {
            sweetalert({
              text: "User added successfully",
              icon: "success",
            });
            this.$store.commit("user/loginName", this.user.name)
            this.$store.commit("user/loginSurname", this.user.surname)
            this.$store.commit("user/loginRole", this.user.role)
            // console.log(this.email)
            this.$router.push("/")
          })
          .catch((err) => {
            if (err.response.status === 409) {
              sweetalert({
                text: "User already exists.",
                icon: "error",
              });
            } else {
              sweetalert({
                text: "Registration failed.",
                icon: "error",
              });
            }
            console.log("Error: ", err);
          });
    },
  },
};
</script>

<style scoped></style>