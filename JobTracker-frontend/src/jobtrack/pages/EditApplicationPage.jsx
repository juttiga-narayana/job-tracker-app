/**
 * EditApplicationPage.jsx
 * ------------------------
 * Page component for editing an existing job application in the Job Tracker app.
 *
 * Purpose:
 *  - Loads application data by ID using useParams from URL.
 *  - Allows users to edit company name, role, application date, and status.
 *  - Submits updated data to the backend API via PUT request.
 *
 * State:
 *  - formData: stores input values for companyName, role, applicationDate, and status.
 *
 * Features:
 *  - Loads existing application data on component mount.
 *  - Handles input changes dynamically.
 *  - Submits updated application and resets form.
 */

import React, { useEffect } from 'react'
import SideBar from '../components/SideBar'
import { useParams } from 'react-router-dom'
import { Link } from 'react-router-dom'
import api from './api'

const EditApplicationPage = () => {
  // === State for form fields ===
  const [formData, setFormData] = React.useState({
    companyName: '',
    role: '',
    applicationDate: '',
    status: '',
  })

  const { id } = useParams() // Get application ID from URL

  /**
   * Updates formData state on input change
   * @param {Object} e - Event object from input/select field
   */
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value })
  }

  // Load existing application data on component mount
  useEffect(() => {
    loadusers()
  }, [])

  /**
   * Handles form submission
   * - Prevents default form behavior
   * - Sends PUT request to update application by ID
   * - Resets form after successful submission
   * @param {Object} e - Form submission event
   */
  const handleSubmit = async (e) => {
    e.preventDefault()

    await api.put(`/jobapplication/applications/${id}`, formData)

    setFormData({
      companyName: '',
      role: '',
      status: '',
      applicationDate: '',
    })
  }

  /**
   * Loads existing application data from backend by ID
   * Sets formData state with fetched data
   */
  const loadusers = async () => {
    const response = await api.get(`/jobapplication/applications/${id}`)
    setFormData(response.data)
  }

  return (
    <div className="mainpage">
      {/* === Sidebar === */}
      <SideBar />

      {/* === Edit Application Form === */}
      <div className="Addmain">
        <h1 className="dashTitle">Edit Application</h1>
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

            {/* Action Buttons */}
            <div className="buttons">
              <Link to="/dashboard">
                <button className="sub-btn2">Cancel</button>
              </Link>
              <button type="submit" className="sub-btn">
                Submit
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}

export default EditApplicationPage