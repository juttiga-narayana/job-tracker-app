/**
 * App.jsx
 * ------------------------
 * Root component of the Job Tracker frontend application.
 *
 * Purpose:
 *  - Serves as the main entry point for all frontend routes.
 *  - Defines the routing structure using React Router v6.
 *  - Connects all pages (Dashboard, Applications, Auth, Profile).
 *
 * Imported Dependencies:
 *  - React: Core library for building UI.
 *  - react-router-dom: Used for client-side routing.
 *  - App.css: Global stylesheet.
 *
 * Imported Pages:
 *  - MainPage: Landing page of the application.
 *  - DashboardPage: Displays job statistics and overview.
 *  - AddapplicationPage: Form to add a new job application.
 *  - EditApplicationPage: Edit an existing job application (dynamic by ID).
 *  - LoginPage: Handles user login.
 *  - RegisterPage: Handles user registration.
 *  - ProfilePage: Displays user profile info.
 *  - UserEditProfile: Edit user profile details.
 *
 * Routing Structure:
 *  - "/" → MainPage
 *  - "/dashboard" → DashboardPage
 *  - "/addapplication" → AddapplicationPage
 *  - "/editapplication/:id" → EditApplicationPage
 *  - "/login" → LoginPage
 *  - "/register" → RegisterPage
 *  - "/profile" → ProfilePage
 *  - "/editprofile" → UserEditProfile
 *
 * Notes:
 *  - Uses <Routes> and <Route> from React Router v6.
 *  - Wraps all routes inside the App component.
 */

import React from 'react'
import MainPage from './jobtrack/pages/MainPage'
import './App.css'
import { Routes, Route } from 'react-router-dom'
import DashboardPage from './jobtrack/pages/DashboardPage'
import AddapplicationPage from './jobtrack/pages/AddapplicationPage'
import EditApplicationPage from './jobtrack/pages/EditApplicationPage'
import LoginPage from './jobtrack/pages/LoginPage'
import RegisterPage from './jobtrack/pages/RegisterPage'
import ProfilePage from './jobtrack/pages/ProfilePage'
import UserEditProfile from './jobtrack/pages/UserEditProfile'
const App = () => {
  return (
    <div>
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/dashboard" element={<DashboardPage />} />
        <Route path="/addapplication" element={<AddapplicationPage />} />
        <Route path="/editapplication/:id" element={<EditApplicationPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/profile" element={<ProfilePage />} />
        <Route path="/editprofile" element={<UserEditProfile />} />
      </Routes>
    </div>
  )
}

export default App
