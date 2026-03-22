<template>
  <div class="reservation-page">
    <div class="reservation-layout">
      <div class="left-panel">
        <ReservationForm />
      </div>

      <div class="right-panel">
        <TableMap />
      </div>
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
  }
}
</script>

<style scoped src="../assets/styles/reservation-view.css"></style>
