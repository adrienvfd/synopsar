'use client'
import {} from 'react-icons/fa6'
import { Link } from 'react-router-dom'

export default function Welcome() {
  return (
    <div className="flex flex-col w-full h-full items-center justify-between manual-y-center overflow-hidden pb-20">
      <div className="flex flex-col w-full items-center justify-center font-mono mb-12">
        <span className="text-3xl mb-8 border rounded-lg py-2 px-3 text-center">
          test1
        </span>
        <span className="text-xl font-light text-amber-400 text-center">
          Test2
        </span>
      </div>
      <div className="flex flex-col w-72 items-start justify-center text-lg">
        <span className="text-xl text-cyan-300 underline mb-4">Test3</span>
        <a
          className="text-purple-400 items-center justify-between flex flex-row w-full"
          href="/docs"
        >
          <span className="text-violet-400">Doc</span>
          <span>/docs</span>
        </a>
        <a
          className="text-purple-400 items-center justify-between flex flex-row w-full"
          href="/api/hello"
        >
          <span className="text-violet-400">GET:</span>
          <span>/api/hello</span>
        </a>
        <a
          className="text-purple-400 items-center justify-between flex flex-row w-full"
          href="/api/user/vitalik"
        >
          <span className="text-violet-400">GET:</span>
          <span>{'/api/user/<username>'}</span>
        </a>
        <a
          className="text-purple-400 items-center justify-between flex flex-row w-full"
          href="/api/completion"
        >
          <span className="text-violet-400">POST:</span>
          <span>/api/completion</span>
        </a>
        <Link
          className="text-green-400 mt-4 items-center justify-between flex flex-row w-full"
          to="profile/adrien"
        >
          <span className="text-lime-500">Page1: </span>
          <span>/#/profile/username</span>
        </Link>
      </div>
    </div>
  )
}
