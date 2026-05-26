/**
 * MainPage.jsx
 * ------------------------
 * Main page component for the Job Tracker application.
 *
 * Purpose:
 *  - Serves as the main entry page for the application.
 *  - Combines the Sidebar and LandingPage components.
 *
 * Features:
 *  - Displays the sidebar for navigation.
 *  - Displays the landing page content for welcome and features.
 */

import React from 'react'
import SideBar from '../components/SideBar'
import LandingPage from '../components/LandingPage'

const MainPage = () => {
  return (
    <div className="main">
      {/* === Sidebar for navigation === */}
      <SideBar />

      {/* === Landing page content === */}
      <LandingPage />
    </div>
  )
}

export default MainPage
