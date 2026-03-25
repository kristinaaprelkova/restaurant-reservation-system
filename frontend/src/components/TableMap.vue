<template>
  <div class="map-container">
    <img src="../assets/restaurant_layout.png" class="map-image" alt="Restaurant layout" />

    <button
        v-for="table in positionedTables"
        :key="table.id"
        type="button"
        class="table-dot"
        :class="getTableClass(table)"
        :style="{ top: table.top + '%', left: table.left + '%' }"
        :title="`Table ${table.tableNumber}, seats: ${table.seats}, zone: ${table.zone}`"
        @click="selectTable(table)"
    >
      {{ table.tableNumber }}
    </button>
  </div>
</template>

<script>
export default {
  name: 'TableMap',
  props: {
    guestCount: Number,
    date: String,
    zone: String,
    selectedTime: String,
    selectedTable: Object
  },
  data() {
    return {
      tables: [],
      layout: [
        { tableNumber: 1, top: 18, left: 14 },
        { tableNumber: 2, top: 46, left: 14.5 },
        { tableNumber: 3, top: 68.5, left: 11 },
        { tableNumber: 4, top: 36, left: 27 },
        { tableNumber: 5, top: 50, left: 27 },
        { tableNumber: 6, top: 65, left: 27 },
        { tableNumber: 7, top: 25, left: 50.0 },
        { tableNumber: 8, top: 25, left: 66 },
        { tableNumber: 9, top: 25, left: 86 },
        { tableNumber: 10, top: 45, left: 38 },
        { tableNumber: 11, top: 48.5, left: 53},
        { tableNumber: 12, top: 61.0, left: 38.0 },
        { tableNumber: 13, top: 70, left: 49 },
        { tableNumber: 14, top: 70, left: 60 },
        { tableNumber: 15, top: 60, left: 83.5 }
      ]
    }
  },
  computed: {
    positionedTables() {
      return this.tables.map(table => {
        const tableLayout = this.layout.find(item => item.tableNumber === table.tableNumber)
        return {
          ...table,
          top: tableLayout ? tableLayout.top : 0,
          left: tableLayout ? tableLayout.left : 0
        }
      })
    }
  },
  watch: {
    guestCount: { immediate: true, handler() { this.fetchTables() } },
    date() { this.fetchTables() },
    zone() { this.fetchTables() },
    selectedTime() { this.fetchTables() }
  },
  methods: {
    async fetchTables() {
      try {
        const params = new URLSearchParams()

        if (this.guestCount) params.append('guestCount', this.guestCount)
        if (this.date) params.append('date', this.date)
        if (this.zone) params.append('zone', this.zone)
        if (this.selectedTime) params.append('time', this.selectedTime + ':00')

        const response = await fetch(`http://localhost:8080/api/tables/filter?${params.toString()}`)
        if (!response.ok) throw new Error('Failed to load tables')

        this.tables = await response.json()

        if (this.selectedTable) {
          const stillExists = this.tables.find(t => t.id === this.selectedTable.id)
          if (!stillExists) {
            this.$emit('select-table', null)
          }
        }
      } catch (error) {
        console.error('Table loading error:', error)
        this.tables = []
      }
    },

    selectTable(table) {
      this.$emit('select-table', table)
    },

    getTableClass(table) {
      if (this.selectedTable && this.selectedTable.id === table.id) {
        return 'selected'
      }
      return 'available'
    }
  }
}
</script>

<style scoped src="../assets/styles/table-map.css"></style>