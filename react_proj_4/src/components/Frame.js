import Menu from "./Menu";
import Navbar from "./Navbar";

const Frame = ({children}) => {
    return (
        <div className="layout-wrapper layout-content-navbar">
            <div className="layout-container">
            <Menu />
                <div className="layout-page">
                    <Navbar />
                    <div className="content-wrapper">
                        <div className="container-xxl flex-grow-1 container-p-y">
                            {children}
                        </div>
                    </div>
                </div>
            </div>
        </div>
            
    );
};

export default Frame;