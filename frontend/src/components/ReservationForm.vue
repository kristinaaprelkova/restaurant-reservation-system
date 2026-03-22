<template>
  <div class="reservation-form">
    <div class="field-row">
      <label>Seltskonna suurus</label>
      <select v-model="guestCount">
        <option v-for="n in 10" :key="n" :value="n">{{ n }} külalast</option>
      </select>
      <span class="chevron">⌄</span>
    </div>

    <div class="field-row">
      <label>Kuupäev</label>
      <input type="date" v-model="date" />
      <span class="chevron">⌄</span>
    </div>

    <div class="field-block">
      <div class="field-block-label">Kellaaeg</div>

      <div class="time-content">
        <h4>Lõuna</h4>
        <div class="time-grid">
          <button
              v-for="time in lunchTimes"
              :key="time"
              :class="{ selected: selectedTime === time }"
              @click="selectTime(time)"
          >
            {{ time }}
          </button>
        </div>

        <h4>Õhtusöök</h4>
        <div class="time-grid">
          <button
              v-for="time in dinnerTimes"
              :key="time"
              :class="{ selected: selectedTime === time }"
              @click="selectTime(time)"
          >
            {{ time }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ReservationForm',
  data() {
    return {
      guestCount: 2,
      date: '2026-03-22',
      selectedTime: '',
      lunchTimes: [
        '11:30', '11:45', '12:00', '12:15', '12:30', '12:45', '13:00',
        '13:15', '13:30', '13:45', '14:00', '14:15', '14:30', '14:45',
        '15:00', '15:15', '15:30', '15:45', '16:00', '16:15', '16:30', '16:45'
      ],
      dinnerTimes: [
        '17:00', '17:30', '19:00', '19:30', '20:00', '20:30', '21:00', '21:30'
      ]
    }
  },
  methods: {
    selectTime(time) {
      this.selectedTime = time
    }
  }
}
</script>

<style scoped src="../assets/styles/reservation-form.css"></style>