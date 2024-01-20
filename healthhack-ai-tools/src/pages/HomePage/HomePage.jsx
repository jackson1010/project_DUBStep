const HomePage = () => {
  return (
    <div className="bg-gray-100 min-h-screen p-8">
      <div className="max-w-2xl mx-auto">
        <h1 className="text-4xl font-bold mb-6">Welcome to Our Healthcare AI Platform</h1>
        <p className="text-lg text-gray-600 mb-8">
          Providing cutting-edge solutions for healthcare innovation and patient care.
        </p>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
          <div className="bg-white p-6 rounded-md shadow-md">
            <h2 className="text-xl font-semibold mb-4">Advanced Diagnostics</h2>
            <p className="text-gray-700">
              Utilize our AI tool for accurate and advanced diagnostics, improving patient outcomes.
            </p>
          </div>

          <div className="bg-white p-6 rounded-md shadow-md">
            <h2 className="text-xl font-semibold mb-4">Personalized Treatment Plans</h2>
            <p className="text-gray-700">
              Tailor treatment plans based on individual patient data, ensuring personalized care.
            </p>
          </div>

          <div className="bg-white p-6 rounded-md shadow-md">
            <h2 className="text-xl font-semibold mb-4">Real-time Patient Monitoring</h2>
            <p className="text-gray-700">
              Monitor patient health in real-time, enabling proactive intervention and care.
            </p>
          </div>
        </div>

        <div className="mt-8">
          <p className="text-xl font-semibold mb-4">Join us in revolutionizing healthcare!</p>
          <p className="text-gray-700">
            Our AI tool is designed to transform the way healthcare is delivered. Embrace innovation,
            improve patient care, and stay at the forefront of medical technology.
          </p>
        </div>

        <div className="mt-8 bg-green-500 text-white rounded-full py-3 px-6 hover:bg-green-600 cursor-pointer">
          Get Started
        </div>
      </div>
    </div>
  );
};

export default HomePage;
