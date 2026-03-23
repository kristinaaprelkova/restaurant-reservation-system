<template>


  <div class="container">
    <div class="left">
      <ReservationForm />
    </div>

    <div class="right">
      <TableMap />
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

<style scoped>
.container {
  display: flex;
  width: 100%;
  gap: 20px;
  box-sizing: border-box;
}

.left {
  width: 50%;
  box-sizing: border-box;
}

.right {
  width: 50%;
  box-sizing: border-box;
}
</style>
