import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import AppIndex from '@/components/home/AppIndex'
import Home from '@/components/home'
import StaffIndex from '@/components/staff/StaffIndex'
import StaffInfo from '@/components/staff/StaffInfo'
import StratumIndex from '@/components/stratum/StratumIndex'
import Section from '@/components/stratum/Section'
import Position from '@/components/stratum/Position'
import StaffPosition from '@/components/staff/StaffPosition'
import Ontrial from '@/components/staff/Ontrial'
import TransferSection from '@/components/staff/TransferSection'
import TransferPosition from '@/components/staff/TransferPosition'
import LeaveWork from '@/components/staff/LeaveWork'
import LeaveStaff from '@/components/home/LeaveStaff'
import InductionIndex from '@/components/Induction/InductionIndex'
import Induction from '@/components/Induction/Induction'
import AddStaff from '@/components/Induction/AddStaff'
import User from '@/components/sys/User'
import Role from '@/components/sys/Role'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // }
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      // home页面并不需要被访问
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/leaveStaff',
          name: 'LeaveStaff',
          component: LeaveStaff,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/staff',
          name: 'StaffIndex',
          component: StaffIndex,
          redirect: '/info',
          meta: {
            requireAuth: true
          },
          children: [
            {
              path: '/info',
              name: 'StaffInfo',
              component: StaffInfo,

            },
            {
              path: '/staff_position',
              name: 'StaffPosition',
              component: StaffPosition
            },
            {
              path: '/ontrial',
              name: 'Ontrial',
              component: Ontrial
            },
            {
              path: '/transfer_section',
              name: 'TransferSection',
              component: TransferSection
            },
            {
              path: '/transfer_position',
              name: 'TransferPosition',
              component: TransferPosition
            },
            {
              path: '/leaveWork',
              name: 'LeaveWork',
              component: LeaveWork
            }
          ]
        },
        {
          path: '/inductionIndex',
          name: 'InductionIndex',
          component: InductionIndex,
          redirect: '/induction',
          children: [
            {
              path: '/induction',
              name: 'Induction',
              component: Induction
            },
            {
              path: '/addStaff',
              name: 'AddStaff',
              component: AddStaff
            }
          ]
        },
        {
          path: '/stratum',
          name: 'Stratum',
          component: StratumIndex,
          redirect: '/section',
          meta: {
            requireAuth: true
          },
          children: [
            {
              path: '/section',
              name: 'Section',
              component: Section
            },
            {
              path: '/position',
              name: 'Position',
              component: Position
            }
          ]
        },
        {
          path: '/sysUser',
          name: User,
          component: User
        },
        {
          path: '/sysRole',
          name: Role,
          component: Role
        }
        // {
        //   path: '/library',
        //   name: 'Library',
        //   component: LibraryIndex,
        //   meta: {
        //     requireAuth: true
        //   }
        // }
      ]
    }
  ]
})
