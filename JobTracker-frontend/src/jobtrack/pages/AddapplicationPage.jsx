/**
 * AddapplicationPage.jsx
 * ------------------------
 * Page component for adding a new job application in the Job Tracker app.
 *
 * Purpose:
 *  - Provides a form for users to input details of a job application.
 *  - Sends the form data to backend API for storage.
 *  - Uses Sidebar component for navigation.
 *
 * State:
 *  - formData: stores input values for companyName, role, applicationDate, and status
 */

import React from 'react'
import SideBar from '../components/SideBar'
import api from './api'

const AddapplicationPage = () => {
  // === State for form fields ===
  const [formData, setFormData] = React.useState({
    companyName: '',
    role: '',
    applicationDate: '',
    status: '',
  })

  /**
   * Updates formData state on input change
   * @param {Object} e - Event object from input/select field
   */
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value })
  }

  /**
   * Handles form submission
   * - Prevents default form behavior
   * - Retrieves userId from localStorage
   * - Sends POST request to API endpoint with formData
   * - Resets form after successful submission
   * @param {Object} e - Form submission event
   */
  const handleSubmit = async (e) => {
    const userId = localStorage.getItem('userId')

    e.preventDefault()

    await api.post(`/jobapplication/users/${userId}/applications`, formData)

    setFormData({
      companyName: '',
      role: '',
      status: 'Applied',
      applicationDate: '',
    })
  }

  return (
    <div className="mainpage">
      {/* === Sidebar Component === */}
      <SideBar />

      {/* === Main Add Application Form === */}
      <div className="Addmain">
        <h1 className="dashTitle">Add Application</h1>

        <div className="formdiv">
          <form onSubmit={handleSubmit}>
            {/* Company Name Field */}
            <div className="formgroup">
              <label htmlFor="company">Company Name</label>
              <input
                type="text"
                id="company"
                name="companyName"
                value={formData.companyName}
                onChange={handleChange}
                required
              />
            </div>

            {/* Role and Application Date Fields */}
            <div className="two">
              <div className="formgroup">
                <label htmlFor="role">Role</label>
                <input
                  type="text"
                  name="role"
                  value={formData.role}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="formgroup">
                <label htmlFor="date">Application Date</label>
                <input
                  type="date"
                  id="date"
                  name="applicationDate"
                  value={formData.applicationDate}
                  onChange={handleChange}
                  required
                />
              </div>
            </div>

            {/* Status Dropdown */}
            <div className="formgroup">
              <label htmlFor="status">Status</label>
              <select
                id="status"
                name="status"
                value={formData.status}
                onChange={handleChange}
                required
              >
                <option value="">Select Status</option>
                <option value="Applied">Applied</option>
                <option value="Interview">Interview</option>
                <option value="Offer">Offer</option>
                <option value="Rejected">Rejected</option>
              </select>
            </div>

            {/* Submit Button */}
            <button type="submit" className="sub-btn">
              Submit
            </button>
          </form>
        </div>
      </div>
    </div>
  )
}

export default AddapplicationPage