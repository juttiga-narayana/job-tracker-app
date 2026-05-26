/**
 * LoginPage.jsx
 * ------------------------
 * Login page component for the Job Tracker application.
 *
 * Purpose:
 *  - Provides a form for users to log in using username and password.
 *  - Sends login credentials to backend API via POST request.
 *  - Stores JWT token and userId in localStorage on successful login.
 *  - Redirects users to the Dashboard page after successful login.
 *
 * State:
 *  - userData: Object storing userName and password entered by the user.
 *  - error: Stores error message if login fails.
 *
 * Features:
 *  - Dynamic input handling using handleChange.
 *  - Handles API login request using handleLogin.
 *  - Decodes JWT token to extract userId.
 *  - Displays error message on login failure.
 */

import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import api from './api'

const LoginPage = () => {
  // === State ===
  const [userData, setUserData] = React.useState({
    userName: '',
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
   * Handles user login
   * - Prevents default form submission
   * - Sends POST request to /login API endpoint
   * - Stores JWT token and userId in localStorage
   * - Navigates to Dashboard page on success
   * - Sets error message on failure
   * @param {Object} e - Form submission event
   */
  const handleLogin = async (e) => {
    e.preventDefault()
    try {
      const response = await api.post('/login', userData)

      const token = response.data
      localStorage.setItem('token', token)

      // Decode payload from JWT to get userId
      const payload = JSON.parse(atob(token.split('.')[1]))
      localStorage.setItem('userId', payload.userId)

      navigate('/dashboard')
    } catch (err) {
      setError(err.response?.data?.message || 'Login failed')
    }
  }

  return (
    <div className="login-page">
      <h2 id="login">Log in</h2>

      {/* === Login Form === */}
      <form onSubmit={handleLogin}>
        {/* Username Field */}
        <div className="log-div">
          <label htmlFor="userName">UserName:</label>
          <input
            type="text"
            id="userName"
            name="userName"
            value={userData.userName}
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
        {error && <p style={{ color: 'red' }}>{error}</p>}

        {/* Submit Button */}
        <button className="log-btn" type="submit">
          Login
        </button>
      </form>

      {/* Registration Link */}
      <p className="register-link">
        Don't have an account? <Link to={'/register'}>Register here</Link>
      </p>
    </div>
  )
}

export default LoginPage