<template>
  <div class="reservation-form">
    <div class="field-row">
      <label>Seltskonna suurus</label>
      <select v-model="localGuestCount">
        <option v-for="n in 10" :key="n" :value="n">{{ n }} külalist</option>
      </select>
    </div>

    <div class="field-row">
      <label>Kuupäev</label>
      <input type="date" v-model="localDate" />
    </div>

    <div class="field-row">
      <label>Tsoon</label>
      <select v-model="localZone">
        <option value="">Vali tsoon</option>
        <option value="terrass">Terrass</option>
        <option value="sisesaal">Sisesaal</option>
        <option value="privaatruum">Privaatruum</option>
      </select>
    </div>

    <div class="field-block">
      <div class="field-block-label">Kellaaeg</div>

      <div class="time-content">
        <h4>Lõuna</h4>
        <div class="time-grid">
          <button
              v-for="time in lunchTimes"
              :key="time"
              type="button"
              :class="{ selected: localSelectedTime === time }"
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
              type="button"
              :class="{ selected: localSelectedTime === time }"
              @click="selectTime(time)"
          >
            {{ time }}
          </button>
        </div>
      </div>
    </div>

    <div class="field-row">
      <label>Nimi</label>
      <input
          type="text"
          v-model="customerName"
          placeholder="Sisesta nimi"
      />
    </div>

    <div class="field-row">
      <label>Email</label>
      <input
          type="email"
          v-model="customerEmail"
          placeholder="Sisesta email"
      />
    </div>

    <div class="field-row" v-if="selectedTable">
      <label>Valitud laud</label>
      <div>
        Laud {{ selectedTable.tableNumber }} · {{ selectedTable.seats }} kohta · {{ selectedTable.zone }}
      </div>
    </div>

    <div class="submit-row">
      <button
          class="reserve-button"
          type="button"
          @click="submitReservation"
      >
        Reserveeri
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ReservationForm',
  props: {
    guestCount: Number,
    date: String,
    zone: String,
    selectedTime: String,
    selectedTable: Object
  },
  data() {
    return {
      customerName: '',
      customerEmail: '',
      localGuestCount: this.guestCount || 2,
      localDate: this.date || '',
      localZone: this.zone || '',
      localSelectedTime: this.selectedTime || '',
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
  watch: {
    localGuestCount(newValue) {
      this.$emit('update-guest-count', newValue)
    },
    localDate(newValue) {
      this.$emit('update-date', newValue)
    },
    localZone(newValue) {
      this.$emit('update-zone', newValue)
    },
    localSelectedTime(newValue) {
      this.$emit('update-selected-time', newValue)
    },

    guestCount(newValue) {
      this.localGuestCount = newValue
    },
    date(newValue) {
      this.localDate = newValue
    },
    zone(newValue) {
      this.localZone = newValue
    },
    selectedTime(newValue) {
      this.localSelectedTime = newValue
    }
  },
  methods: {
    selectTime(time) {
      this.localSelectedTime = time
    },

    async submitReservation() {
      if (!this.customerName || !this.customerEmail || !this.localDate || !this.localZone || !this.localSelectedTime) {
        alert('Please fill in all fields and select a time.')
        return
      }

      if (!this.selectedTable) {
        alert('Please select a table on the map.')
        return
      }

      try {
        const response = await fetch('https://restaurant-reservation-system-fsv6.onrender.com/api/reservations', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            customerName: this.customerName,
            customerEmail: this.customerEmail,
            reservationDate: this.localDate,
            reservationTime: this.localSelectedTime + ':00',
            guestCount: this.localGuestCount,
            zone: this.localZone,
            table: {
              id: this.selectedTable.id
            }
          })
        })

        const responseText = await response.text()

        if (!response.ok) {
          throw new Error(responseText || 'Failed to save reservation')
        }

        alert('Reservation saved successfully!')

        this.customerName = ''
        this.customerEmail = ''
        this.localSelectedTime = ''
      } catch (error) {
        console.error('Reservation error:', error)
        alert('Error while saving reservation: ' + error.message)
      }
    }
  }
}
</script>

<style scoped src="../assets/styles/reservation-form.css"></style>