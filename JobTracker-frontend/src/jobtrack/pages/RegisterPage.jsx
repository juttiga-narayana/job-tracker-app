/**
 * RegisterPage.jsx
 * ------------------------
 * Registration page component for the Job Tracker application.
 *
 * Purpose:
 *  - Allows new users to create an account by providing full name, email, and password.
 *  - Sends user registration data to backend API via POST request.
 *  - Redirects users to login page upon successful registration.
 *
 * State:
 *  - userData: Object storing userName, userEmail, and password input values.
 *  - error: Stores error message if registration fails.
 *
 * Features:
 *  - Dynamic input handling using handleChange.
 *  - Handles form submission using handleLogin.
 *  - Displays error message if API registration fails.
 */

import React from 'react'
import { Link } from 'react-router-dom'
import { useNavigate } from 'react-router-dom'
import api from './api'

const RegisterPage = () => {
  // === State for user registration form ===
  const [userData, setUserData] = React.useState({
    userName: '',
    userEmail: '',
    password: '',
  })
  const [error, setError] = React.useState('')
  const navigate = useNavigate()

  /**
   * Handles input field changes and updates userData state
   * @param {Object} e - Event object from input field
   */
  const handleChange = (e) => {
    setUserData({ ...userData, [e.target.name]: e.target.value })
  }

  /**
   * Handles user registration
   * - Prevents default form submission
   * - Sends POST request to /register API endpoint
   * - Redirects to login page on success
   * - Sets error state if registration fails
   * @param {Object} e - Form submission event
   */
  const handleLogin = async (e) => {
    e.preventDefault()
    try {
      const response = await api.post('/register', userData)
      navigate('/login')
    } catch (err) {
      setError(err)
    }
  }

  return (
    <div>
      <div className="login-page register-page">
        <h2 id="login">Create your account</h2>

        {/* === Registration Form === */}
        <form onSubmit={handleLogin}>
          {/* Full Name Field */}
          <div className="log-div">
            <label htmlFor="userName">Full Name:</label>
            <input
              type="text"
              id="userName"
              name="userName"
              value={userData.userName}
              onChange={handleChange}
              required
            />
          </div>

          {/* Email Field */}
          <div className="log-div">
            <label htmlFor="userEmail">Email:</label>
            <input
              type="email"
              id="userEmail"
              name="userEmail"
              value={userData.userEmail}
              onChange={handleChange}
              required
            />
          </div>

          {/* Password Field */}
          <div className="log-div">
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
              name="password"
              value={userData.password}
              onChange={handleChange}
              required
            />
          </div>

          {/* Error Message */}
          {error && <p style={{ color: 'red' }}>{error.message || error}</p>}

          {/* Submit Button */}
          <button className="log-btn" type="submit">
            Create account
          </button>
        </form>

        {/* Login Link */}
        <p className="register-link">
          Already have an account? <Link to={'/login'}>Log in</Link>
        </p>
      </div>
    </div>
  )
}

export default RegisterPage
