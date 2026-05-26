/**
 * LandingPage.jsx
 * ----------------
 * Landing page component for the Job Tracker application.
 *
 * Purpose:
 *  - Acts as the entry point for first-time or unauthenticated users.
 *  - Introduces the app with a welcome message, feature highlights,
 *    and a call-to-action (login).
 *
 * Sections:
 *  - Header: Welcome text and description
 *  - Features: Key benefits of using Job Tracker
 *  - CTA (Call-to-Action): Button linking to login page
 *  - Footer (optional, currently commented out)
 */

import React from 'react'
import './LandingPage.css' // Styles specific to LandingPage
import { Link } from 'react-router-dom'

const LandingPage = () => {
  return (
    <div className="landing-container">
      {/* === Header Section === */}
      <header className="header">
        <h1>Welcome to Job Tracker!</h1>
        <p>
          Your ultimate tool for tracking job applications and opportunities.
        </p>
      </header>

      {/* === Features Section === */}
      <section className="features">
        <h2>Features</h2>
        <ul>
          <li>Track your job applications in one place</li>
          <li>Set reminders for follow-ups</li>
          <li>Get insights on your job search progress</li>
          <li>Save job listings from various platforms</li>
        </ul>
      </section>

      {/* === Call-to-Action Section === */}
      <section className="cta">
        <h2>Ready to take control of your job search?</h2>
        <Link to={'/login'}>
          {/* Navigates to login page */}
          <button className="cta-button">Log in</button>
        </Link>
      </section>
    </div>
  )
}

export default LandingPage
