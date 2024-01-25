import { useAuth0 } from "@auth0/auth0-react";
import { useNavigate } from "react-router-dom";

const ProfilePage = () => {
  const { user, isAuthenticated, isLoading } = useAuth0();
  const navigate = useNavigate();

  if (isLoading) {
    return <div>Loading ...</div>;
  }
  const handleGoBack = () => {
    navigate("/");
  };

  return (
    <>
      <button type="button" onClick={handleGoBack}>
        Natrag na početnu stranicu
      </button>
      {isAuthenticated && (
        <div>
          {user?.picture && <img src={user.picture} alt={user?.name} />}
          <h2>{user?.name}</h2>
          <ul>
            {Object.keys(user).map((objKey, i) => (
              <li key={i}>
                {objKey}: {user[objKey]}
              </li>
            ))}
          </ul>
        </div>
      )}
      {!isAuthenticated && (
        <div>
          <p>Error - Not authenticated</p>
          {(() => {
            console.error("Error - Not authenticated");
            return null;
          })()}
        </div>
      )}
    </>
  );
};

export default ProfilePage;
