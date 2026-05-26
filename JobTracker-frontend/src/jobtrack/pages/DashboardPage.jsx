/**
 * DashboardPage.jsx
 * ------------------------
 * Dashboard page for the Job Tracker application.
 *
 * Purpose:
 *  - Displays a summary of the user's job applications (statistics and table).
 *  - Allows users to view, edit, or delete job applications.
 *
 * State:
 *  - applications: Array of user's job applications fetched from backend.
 *  - stats: Object containing totalApplications, totalInterviews, and totalOffers.
 *
 * Features:
 *  - Fetch applications on component mount using useEffect.
 *  - Delete applications and refresh table.
 *  - Edit applications via routing to EditApplicationPage.
 */

import SideBar from '../components/SideBar'
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import api from './api' // Axios instance with JWT interceptor

const DashboardPage = () => {
  // === State ===
  const [applications, setApplications] = useState([])
  const [stats, setStats] = useState({})

  // === Fetch applications on component mount ===
  useEffect(() => {
    fetchApplications()
  }, [])

  /**
   * Fetch all applications for the current user from backend
   * Updates applications state and statistics
   */
  const fetchApplications = async () => {
    const userId = localStorage.getItem('userId')
    const response = await api.get(
      `/jobapplication/users/${userId}/applications`,
    )

    const { applications, totalApplications, totalOffers, totalInterviews } =
      response.data

    setApplications(applications)
    setStats({ totalApplications, totalOffers, totalInterviews })
  }

  /**
   * Deletes a job application by ID
   * @param {string} id - The ID of the application to delete
   */
  const handleDelete = async (id) => {
    await api.delete(`/jobapplication/applications/${id}`)
    fetchApplications()
  }

  return (
    <div className="mainpage">
      {/* === Sidebar === */}
      <SideBar />

      {/* === Dashboard Main Content === */}
      <div className="dashmain">
        <h1 className="dashTitle">Dashboard</h1>

        {/* === Statistics Cards === */}
        <div className="subdiv">
          <div className="d">
            Total Applications <br />
            {stats.totalApplications}
          </div>
          <div className="d">
            Interviews <br />
            {stats.totalInterviews}
          </div>
          <div className="d">
            Offers <br />
            {stats.totalOffers}
          </div>
        </div>

        {/* === Applications Table === */}
        <div
          className="applicationmain"
          aria-label="Scrollable data table with sticky header"
        >
          <h1>Application</h1>
          <div className="tablediv">
            <table>
              <thead>
                <tr>
                  <th>Sno</th>
                  <th>Company</th>
                  <th>Role</th>
                  <th>Date</th>
                  <th>Status</th>
                  <th> </th>
                </tr>
              </thead>
              <tbody>
                {applications.map((item, index) => (
                  <tr key={item.id}>
                    <td key={index}>{index + 1}</td>
                    <td>{item.companyName}</td>
                    <td>{item.role}</td>
                    <td>{item.applicationDate}</td>
                    <td>{item.status}</td>
                    <td>
                      <div className="twobtn">
                        {/* Edit Button */}
                        <button className="dash-btn edit">
                          <Link to={`/editapplication/${item.id}`}>Edit</Link>
                        </button>
                        {/* Delete Button */}
                        <button
                          className="dash-btn delete"
                          onClick={() => handleDelete(item.id)}
                        >
                          Delete
                        </button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  )
}

export default DashboardPage
