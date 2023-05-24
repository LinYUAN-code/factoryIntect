<script setup>
const props = defineProps({
  name: {
    type: String,
    default: "无",
  },
});
const colors = [
  "#f6e58d",
  "#ffbe76",
  "#ff7979",
  "#badc58",
  "#7ed6df",
  "#dff9fb",
];
let color = "#f6e58d";
const name = props.name;
if (localStorage.getItem(name)) {
  color = localStorage.getItem(name);
} else {
  color = colors[Math.floor(Math.random() * 6)];
  localStorage.setItem(name, color);
}
</script>
 
<template>
  <div class="avatar" :style="{ backgroundColor: color }">
    {{ name[0] }}
  </div>
</template>

<style lang='scss' scoped>
.avatar {
  width: 45px;
  height: 45px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;

  font-size: 23px;
  font-weight: 500;
  font-family: "STXingkai";
}
</style>