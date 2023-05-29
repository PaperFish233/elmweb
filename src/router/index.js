import { createRouter, createWebHashHistory } from 'vue-router'
import Index from '../views/Index.vue'
import BusinessList from '../views/BusinessList.vue'
import BusinessInfo from '../views/BusinessInfo.vue'
import Login from '../views/Login.vue'
import Order from '../views/Order.vue'
import AddressInfo from '../views/AddressInfo.vue'
import AddUserAddress from '../views/AddUserAddress.vue'
import EditUserAddress from '../views/EditUserAddress.vue'
import Payment from '../views/Payment.vue'
import OrderList from '../views/OrderList.vue'
import Register from '../views/Register.vue'

const routes = [
  {
    path: '/',
    name: 'index',
    component: Index
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/businessList',
    name: 'businessList',
    component: BusinessList
  },
  {
    path: '/businessInfo',
    name: 'businessInfo',
    component: BusinessInfo
  },
  {
    path: '/order',
    name: 'order',
    component: Order
  },
  {
    path: '/addressinfo',
    name: 'addressinfo',
    component: AddressInfo
  },
  {
    path: '/adduseraddress',
    name: 'adduseraddress',
    component: AddUserAddress
  },
  {
    path: '/edituseraddress',
    name: 'edituseraddress',
    component: EditUserAddress
  },
  {
    path: '/payment',
    name: 'payment',
    component: Payment
  },
  {
    path: '/orderlist',
    name: 'orderlist',
    component: OrderList
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
