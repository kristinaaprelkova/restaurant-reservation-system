<template>
  <div class="table-map">
    <img :src="layoutImage" alt="Restaurant layout" />
  </div>
</template>

<script>
import layoutImage from '../assets/restaurant_layout.png'

export default {
  name: 'TableMap',
  data() {
    return {
      layoutImage
    }
  }
}
</script>

<style scoped>
.table-map {
  background: #f7f7f7;
  border-radius: 16px;
  padding: 16px;
}

.table-map img {
  width: 100%;
  border-radius: 12px;
}
</style>