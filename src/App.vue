<template>
  <router-view/>
</template>

<script setup>
import axios from 'axios'
import { provide } from "vue";

//设置axios的基础url部分
axios.defaults.baseURL = 'http://localhost:8080/elmweb/';
provide('axios',axios);
</script>

<!-- 这里是共通样式，适用于所有组件，所以不要加scoped -->
<style>
	html,
	body,
	div,
	span,
	h1,
	h2,
	h3,
	h4,
	h5,
	h6,
	ul,
	ol,
	li,
	p {
		margin: 0;
		padding: 0;
	}

	html,
	body,
	#app {
		width: 100%;
		height: 100%;
		font-family: "微软雅黑";
	}

	ul,
	ol {
		list-style: none;
	}

	a {
		text-decoration: none;
	}
</style>
