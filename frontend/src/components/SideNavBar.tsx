'use Client'

import React from 'react';
import Link from 'next/link';

const SideNavBar: React.FC = () => {

    return (
        <div className="side-nav-bar">
            <ul>
                <li>
                    <Link href="/home">Home</Link>
                </li>
                <li>
                    <Link href="/about">About</Link>
                </li>
                <li>
                    <Link href="/services">Services</Link>
                </li>
                <li>
                    <Link href="/contact">Contact</Link>
                </li>
            </ul>
        </div>
    );
};

export default SideNavBar;