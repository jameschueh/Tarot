import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router)

export default new Router({
  routes: [
    {
      name: 'Index',
      path: '/Index',
      component: () => import('@/components/Index'),
    },
    {
      name: 'CardMean',
      path: '/CardMean',
      component: () => import('@/components/CardMean'),
    },
    {
      name: 'DrawCard',
      path: '/DrawCard',
      component: () => import('@/components/DrawCard'),
    },
    {
      name: 'CardLog',
      path: '/CardLog',
      component: () => import('@/components/CardLog'),
    },
    {
      path: '*',
      redirect: '/Index',
      hidden: true
    },
  ]
})


const VueRouterPush = Router.prototype.push
Router.prototype.push = function push (to) {
  return VueRouterPush.call(this, to).catch(err => err)
}
