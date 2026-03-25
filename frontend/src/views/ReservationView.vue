<template>
  <div class="container">
    <div class="left">
      <ReservationForm
          :guest-count="guestCount"
          :date="date"
          :zone="zone"
          :selected-time="selectedTime"
          :selected-table="selectedTable"
          @update-guest-count="guestCount = $event"
          @update-date="date = $event"
          @update-zone="zone = $event"
          @update-selected-time="selectedTime = $event"
      />
    </div>

    <div class="right">
      <TableMap
          :guest-count="guestCount"
          :date="date"
          :zone="zone"
          :selected-time="selectedTime"
          :selected-table="selectedTable"
          @select-table="selectedTable = $event"
      />
    </div>
  </div>
</template>

<script>
import ReservationForm from '../components/ReservationForm.vue'
import TableMap from '../components/TableMap.vue'

export default {
  components: {
    ReservationForm,
    TableMap
  },
  data() {
    return {
      guestCount: 2,
      date: '',
      zone: '',
      selectedTime: '',
      selectedTable: null
    }
  },
  mounted() {
    const today = new Date()
    this.date = today.toISOString().split('T')[0]
  }
}
</script>

<style scoped>
.container {
  display: flex;
  width: 100%;
  gap: 20px;
  box-sizing: border-box;
}

.left,
.right {
  width: 50%;
  box-sizing: border-box;
}
</style>
