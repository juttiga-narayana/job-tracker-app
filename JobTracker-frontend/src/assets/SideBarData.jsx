/**
 * SideBarData.jsx
 * ----------------
 * This file defines static data used for the application's sidebar and user menu.
 * It provides menu items with titles, icons, and navigation links.
 *
 * Organized into separate arrays for:
 *   - Main Sidebar (Dashboard, Add Application)
 *   - User Menu (Profile)
 *   - Profile Picture (Icon only)
 *   - Logout Button (Icon only)
 */

import React from 'react'
import HomeIcon from '@mui/icons-material/Home'
import FormatListBulletedAddIcon from '@mui/icons-material/FormatListBulletedAdd'
import PersonIcon from '@mui/icons-material/Person'
import AccountCircleIcon from '@mui/icons-material/AccountCircle'
import LogoutIcon from '@mui/icons-material/Logout'

/**
 * Sidebar navigation items for the main dashboard section
 */
export const SideBarData = [
  {
    title: 'Dashboard',
    icon: <HomeIcon />,
    link: '/dashboard',
  },
  {
    title: 'Add Application',
    icon: <FormatListBulletedAddIcon />,
    link: '/addapplication',
  },
]

/**
 * User-specific menu items (accessible in profile menu)
 */
export const UserData = [
  {
    title: 'Profile',
    icon: <PersonIcon />,
    link: '/profile',
  },
]

/**
 * Profile picture representation in the sidebar
 */
export const ProfilePic = [
  {
    title: 'Profile',
    icon: <AccountCircleIcon style={{ fontSize: 125 }} />,
  },
]

/**
 * Logout option for ending the user session
 */
export const Logout = [
  {
    title: 'Logout',
    icon: <LogoutIcon />,
  },
]
