/**
 * ProfilePage.jsx
 * ------------------------
 * Profile page component for the Job Tracker application.
 *
 * Purpose:
 *  - Displays the logged-in user's profile information including username, email, and total applications.
 *  - Allows the user to edit or delete their profile.
 *
 * State:
 *  - user: Object storing user's username, email, and totalApplications.
 *
 * Features:
 *  - Loads user profile data from backend on component mount.
 *  - Handles profile deletion and redirects to login page.
 *  - Displays profile picture using ProfilePic array from SideBarData.
 */

import React, { useEffect } from 'react'
import SideBar from '../components/SideBar'
import { ProfilePic } from '../../assets/SideBarData'
import api from './api'
import { Link, useNavigate } from 'react-router-dom'

const ProfilePage = () => {
  const navigate = useNavigate()

  // === State for user profile data ===
  const [user, setUser] = React.useState({
    username: '',
    email: '',
    totalApplications: 0,
  })

  // Load user profile on component mount
  useEffect(() => {
    loadUserProfile()
  }, [])

  /**
   * Fetches user profile data from backend and updates state
   */
  const loadUserProfile = async () => {
    const userId = localStorage.getItem('userId')
    const response = await api.get(`/users/${userId}`)
    setUser(response.data)
  }

  /**
   * Deletes the current user's profile and redirects to login page
   */
  const handleDelete = async () => {
    const userId = localStorage.getItem('userId')
    await api.delete(`/user/${userId}`)
    navigate('/login')
  }

  return (
    <div className="mainpage">
      {/* === Sidebar === */}
      <SideBar />

      {/* === Profile Section === */}
      <div className="profilemain">
        {/* Profile Picture */}
        <div className="profilepic">
          {ProfilePic.map((item, index) => (
            <div className="profile" key={index}>
              <div className="profileIcon">{item.icon}</div>
            </div>
          ))}
        </div>

        {/* Profile Information */}
        <div className="profile-container">
          <div className="profile-info">
            <div className="row">
              <div className="label">Name:</div>
              <div className="value">{user.userName}</div>
            </div>
            <div className="row">
              <div className="label">Email:</div>
              <div className="value">{user.userEmail}</div>
            </div>
            <div className="row">
              <div className="label">Total Application:</div>
              <div className="value">{user.totalApplications}</div>
            </div>
          </div>
        </div>

        {/* Action Buttons */}
        <div className="profile-btn">
          <Link to={'/editprofile'}>
            <button className="profile-btn-edit">Edit Profile</button>
          </Link>
          <button className="profile-btn-delete" onClick={handleDelete}>
            Delete
          </button>
        </div>
      </div>
    </div>
  )
}

export default ProfilePage