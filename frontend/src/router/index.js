import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import DuplicatasView from '../views/Duplicatas/Duplicata.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: DuplicatasView
    },
    {
      path: '/swagger',
      name: 'swagger',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/Swagger.vue')
    },
    {
      path: '/duplicatas',
      name: 'duplicatas',
      component: DuplicatasView
    }
  ]
})

export default router
