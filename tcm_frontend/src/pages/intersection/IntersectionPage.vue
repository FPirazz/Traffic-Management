<template>
  <div class="container">
    <div class="row">
      <div class="col-sm">
        <p>Traffic Light 1</p>
        <p v-if="tl1.state == 'red'" class="text-white bg-danger">State - {{ tl1.state }}</p>
        <p v-else-if="tl1.state == 'yellow'" class="bg-warning">State - {{ tl1.state }}</p>
        <p v-else class="text-white bg-success">State - {{ tl1.state }}</p>
        <p>Total Vehicles - {{ tl1.totalVehicles }}</p>
        <p>Normal Vehicles - {{ tl1.normalVehicles }}</p>
        <p>Emergency Vehicles - {{ tl1.emergencyVehicles }}</p>
        <button type="button" class="btn btn-primary" @click="this.addNormalVehicle1">Add Normal Vehicle</button>
        <button type="button" class="btn btn-secondary" @click="this.addEmergencyVehicle1">Add Emergency Vehicle</button>
      </div>
      <div class="col-sm">
        <p>Traffic Light 2</p>
        <p v-if="tl2.state == 'red'" class="text-white bg-danger">State - {{ tl2.state }}</p>
        <p v-else-if="tl2.state == 'yellow'" class="bg-warning">State - {{ tl2.state }}</p>
        <p v-else class="text-white bg-success">State - {{ tl2.state }}</p>
        <p>Total Vehicles - {{ tl2.totalVehicles }}</p>
        <p>Normal Vehicles - {{ tl2.normalVehicles }}</p>
        <p>Emergency Vehicles - {{ tl2.emergencyVehicles }}</p>
        <button type="button" class="btn btn-primary" @click="this.addNormalVehicle2">Add Normal Vehicle</button>
        <button type="button" class="btn btn-secondary" @click="this.addEmergencyVehicle2">Add Emergency Vehicle</button>
      </div>
      <div class="col-sm">
        <p>Traffic Light 3</p>
        <p v-if="tl3.state == 'red'" class="text-white bg-danger">State - {{ tl3.state }}</p>
        <p v-else-if="tl3.state == 'yellow'" class="bg-warning">State - {{ tl3.state }}</p>
        <p v-else class="text-white bg-success">State - {{ tl3.state }}</p>
        <p>Total Vehicles - {{ tl3.totalVehicles }}</p>
        <p>Normal Vehicles - {{ tl3.normalVehicles }}</p>
        <p>Emergency Vehicles - {{ tl3.emergencyVehicles }}</p>
        <button type="button" class="btn btn-primary" @click="this.addNormalVehicle3">Add Normal Vehicle</button>
        <button type="button" class="btn btn-secondary" @click="this.addEmergencyVehicle3">Add Emergency Vehicle</button>
      </div>
      <div class="col-sm">
        <p>Traffic Light 4</p>
        <p v-if="tl4.state == 'red'" class="text-white bg-danger">State - {{ tl4.state }}</p>
        <p v-else-if="tl4.state == 'yellow'" class="bg-warning">State - {{ tl4.state }}</p>
        <p v-else class="text-white bg-success">State - {{ tl4.state }}</p>
        <p>Total Vehicles - {{ tl4.totalVehicles }}</p>
        <p>Normal Vehicles - {{ tl4.normalVehicles }}</p>
        <p>Emergency Vehicles - {{ tl4.emergencyVehicles }}</p>
        <button type="button" class="btn btn-primary" @click="this.addNormalVehicle4">Add Normal Vehicle</button>
        <button type="button" class="btn btn-secondary" @click="this.addEmergencyVehicle4">Add Emergency Vehicle</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import sweetalert from "sweetalert"
import { mapGetters, } from "vuex";

export default {
  data() {
    return {
      polling: null,
      tl1: {
        totalVehicles: 0,
        normalVehicles: 0,
        emergencyVehicles: 0,
        state: "",
      },
      tl2: {
        totalVehicles: 0,
        normalVehicles: 0,
        emergencyVehicles: 0,
        state: "",
      },
      tl3: {
        totalVehicles: 0,
        normalVehicles: 0,
        emergencyVehicles: 0,
        state: "",
      },
      tl4: {
        totalVehicles: 0,
        normalVehicles: 0,
        emergencyVehicles: 0,
        state: "",
      },
    }
  },
  computed: {

  },
  methods: {
    async getIntersection() {
      try {
        await axios.get("http://localhost:8085/tl1")
        .then(res => {
          var content = res.data.properties;
          this.tl1.emergencyVehicles = content[1].emergencyVehicles[0];
          this.tl1.normalVehicles = content[3].normalVehicles[0];
          this.tl1.totalVehicles = content[2].totalVehicles[0];
          this.tl1.state = content[4].trState1[0];
        });

        await axios.get("http://localhost:8085/tl2")
            .then(res => {
              var content = res.data.properties;
              this.tl2.emergencyVehicles = content[0].emergencyVehicles[0];
              this.tl2.normalVehicles = content[3].normalVehicles[0];
              this.tl2.totalVehicles = content[2].totalVehicles[0];
              this.tl2.state = content[4].trState2[0];
            });

        await axios.get("http://localhost:8085/tl3")
            .then(res => {
              var content = res.data.properties;
              this.tl3.emergencyVehicles = content[1].emergencyVehicles[0];
              this.tl3.normalVehicles = content[4].normalVehicles[0];
              this.tl3.totalVehicles = content[3].totalVehicles[0];
              this.tl3.state = content[0].trState3[0];
            });

        await axios.get("http://localhost:8085/tl4")
            .then(res => {
              var content = res.data.properties;
              this.tl4.emergencyVehicles = content[1].emergencyVehicles[0];
              this.tl4.normalVehicles = content[4].normalVehicles[0];
              this.tl4.totalVehicles = content[2].totalVehicles[0];
              this.tl4.state = content[0].trState4[0];
            });
      } catch (err) {
        console.log(err);
      }
    },

    async addNormalVehicle1() {
      try {
        await axios.post("http://localhost:8085/tl1AddNormalVehicle", [], {
          Headers: {
            "Content-Type": "application/json",
          }
        })
      } catch (err) {
        console.log(err);
      }
    },
    async addNormalVehicle2() {
      try {
        await axios.post("http://localhost:8085/tl2AddNormalVehicle", [], {
          Headers: {
            "Content-Type": "application/json",
          }
        })
      } catch (err) {
        console.log(err);
      }
    },
    async addNormalVehicle3() {
      try {
        await axios.post("http://localhost:8085/tl3AddNormalVehicle", [], {
          Headers: {
            "Content-Type": "application/json",
          }
        })
      } catch (err) {
        console.log(err);
      }
    },
    async addNormalVehicle4() {
      try {
        await axios.post("http://localhost:8085/tl4AddNormalVehicle", [], {
          Headers: {
            "Content-Type": "application/json",
          }
        })
      } catch (err) {
        console.log(err);
      }
    },

    async addEmergencyVehicle1() {
      try {
        await axios.post("http://localhost:8085/tl1AddEmergencyVehicle", [], {
          Headers: {
            "Content-Type": "application/json",
          }
        })
      } catch (err) {
        console.log(err);
      }
    },
    async addEmergencyVehicle2() {
      try {
        await axios.post("http://localhost:8085/tl2AddEmergencyVehicle", [], {
          Headers: {
            "Content-Type": "application/json",
          }
        })
      } catch (err) {
        console.log(err);
      }
    },
    async addEmergencyVehicle3() {
      try {
        await axios.post("http://localhost:8085/tl3AddEmergencyVehicle", [], {
          Headers: {
            "Content-Type": "application/json",
          }
        })
      } catch (err) {
        console.log(err);
      }
    },
    async addEmergencyVehicle4() {
      try {
        await axios.post("http://localhost:8085/tl4AddEmergencyVehicle", [], {
          Headers: {
            "Content-Type": "application/json",
          }
        })
      } catch (err) {
        console.log(err);
      }
    },

    async pollData() {
      this.polling = () => {
        setTimeout(async () => {
          await this.getIntersection();
          this.polling();
        }, 750);
      };
      this.polling();
    },

    async isOperatorLogged() {
    },
  },
  beforeUnmount () {
    this.polling = null;
  },
  async mounted() {
    await this.getIntersection();
    await this.isOperatorLogged();
    await this.pollData();
  },

};
</script>