/**
 * SideBar.jsx
 * ----------------
 * Sidebar navigation component for the Job Tracker application.
 *
 * Purpose:
 *  - Displays navigation options for users (Profile, Dashboard, Add Application, etc.).
 *  - Includes a logout option that clears authentication data and redirects to login page.
 *
 * Data Sources:
 *  - UserData: Profile-related items
 *  - SideBarData: Main application navigation items
 *  - Logout: Logout button (triggers handleLogout)
 */

import React from 'react'
import { SideBarData } from '../../assets/SideBarData'
import { UserData } from '../../assets/SideBarData'
import { Logout } from '../../assets/SideBarData'
import { Link, useNavigate } from 'react-router-dom'

const SideBar = () => {
  const navigate = useNavigate()

  /**
   * Logs the user out by:
   *  - Removing JWT token & userId from localStorage
   *  - Redirecting back to login page
   */
  const handleLogout = () => {
    localStorage.removeItem('token')
    localStorage.removeItem('userId')
    navigate('/login')
  }

  return (
    <>
      <div className="side-bar">
        {/* === Logo Section === */}
        <div>
          <div className="logo">
            <img src="/picbox/jobtracker-logo.png" alt="Job Tracker Logo" />
            <h1>Job Tracker</h1>
          </div>

          {/* === Navigation Sections === */}
          <div className="sections">
            {/* User-specific menu (Profile) */}
            {UserData.map((item, index) => (
              <div className="section" key={index}>
                <Link to={item.link}>
                  <div className="content">
                    <div className="icon">{item.icon}</div>
                    <div className="title">{item.title}</div>
                  </div>
                </Link>
              </div>
            ))}

            <hr />

            {/* Main sidebar navigation (Dashboard, Add Application) */}
            {SideBarData.map((item, index) => (
              <div className="section" key={index}>
                <Link to={item.link}>
                  <div className="content">
                    <div className="icon">{item.icon}</div>
                    <div className="title">{item.title}</div>
                  </div>
                </Link>
              </div>
            ))}

            {/* Logout Section */}
            {Logout.map((item, index) => (
              <div className="section logout-sec" key={index}>
                <div className="content" onClick={() => handleLogout()}>
                  <div className="icon">{item.icon}</div>
                  <div className="title">{item.title}</div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </>
  )
}

export default SideBar