/**
 * api.js
 * ----------------
 * Axios instance for making HTTP requests to the backend API.
 *
 * Purpose:
 *  - Sets the base URL for all API calls using environment variable.
 *  - Automatically attaches JWT token from localStorage to the Authorization header
 *    for authenticated requests.
 */

import axios from 'axios'

// Create an Axios instance with base URL from environment variable
const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
})

// Add a request interceptor to include JWT token if available
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}` // Attach token to header
  }
  return config
})

export default api
