<template>
	<div id="app">
		<HelloWorld />
	</div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
	name: 'App',
	components: {
		HelloWorld
	},
}
</script>

<style>
#app {
	display: flex;
	justify-content: center;
}
</style>
