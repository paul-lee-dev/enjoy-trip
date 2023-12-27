import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from '@/views/AboutView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/articles',
      name: 'articles',
      component: () => import('../views/ArticleView.vue'),
      redirect: { name: 'article-list' },
      children: [
        {
          path: '/',
          name: 'article-list',
          component: () => import('@/components/article/ArticleList.vue')
        },
        {
          path: 'detail/:articleId',
          name: 'article-detail',
          component: () => import('@/components/article/ArticleDetail.vue')
        },
        {
          path: 'write',
          name: 'article-write',
          component: () => import('@/components/article/ArticleWrite.vue')
        }
      ]
    },
    {
      path: '/user',
      name: 'user',
      component: () => import('../views/ArticleView.vue'),
      redirect: { name: 'home' },
      children: [
        {
          path: 'regist',
          name: 'user-regist',
          component: () => import('@/components/user/JoinView.vue')
        },
        {
          path: 'modify',
          name: 'user-modify',
          component: () => import('@/components/user/EditInfo.vue')
        },
        {
          path: 'detail',
          name: 'user-detail',
          component: () => import('@/components/user/MyPageInfo\.vue')
        }
      ]
    },
    {
      path: '/plan',
      name: 'plan',
      // component: TheArticleView,
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/PlanView.vue'),
      redirect: { name: 'plan-list' },
      children: [
        {
          path: 'list',
          name: 'plan-list',
          component: () => import('@/components/plan/PlanList.vue')
        },
        {
          path: 'detail/:planId',
          name: 'plan-detail',
          component: () => import('@/components/plan/PlanDetail.vue')
        },
        {
          path: 'create',
          name: 'plan-create',
          component: () => import('@/components/plan/PlanCreate.vue')
        }
      ]
    },
    {
      path: '/',
      name: 'about',
      component: AboutView
    }
  ]
})

export default router
