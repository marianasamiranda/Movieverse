import React, { Component } from 'react';
import Comment from './comment'
import Image from 'react-bootstrap/Image'
import ResizableTextarea from './resizable-text-area'
import '../../styles/Comment.css';

export default class DiscussionBox extends Component {
  render() {
    return <div>
      <div class="comment-container">
				<div className="info d-flex">
					<Image className="profile-pic p-2" src="https://via.placeholder.com/70" />
					<div className="info-author p-2">
						Kim Possible
					</div>
				</div>
				<ResizableTextarea/>
			</div>

			<Comment profilepic="https://via.placeholder.com/70" author="George W. Bush" time="1h ago" content="i took my friend to see this and a week later she said it affected her so much that she cried about it for an entire therapy session, which is probably yorgos’s ultimate artistic goal and would mean more to him than any of the major directing awards that he should be earning!" likes={ 10 }/>

			<Comment profilepic="https://via.placeholder.com/70" author="Woody Allen" time="4h ago" content="i think i reached level 10 surrealism in seeing a yorgos lanthimos movie at 10:30pm when everything else around me was closed and when i got out it was all so dark and not a soul around me except the couple who also saw this in my theater. no one said anything but i knew we were all silently afraid yorgos was crawling on the ceiling above us and was about to unleash a pot of lobsters with knives taped to them on our heads." likes={ 65 }/>

			<Comment profilepic="https://via.placeholder.com/70" author="Emma Stone" time="10h ago" content="oh my god. you guys are sure as hell aren’t ready for this movie. It’s fucking bonkers. It’s this year’s the lobster for lanthimos. I deeply fell in love with the cinematography, the dark comedic script and the costume design especially for me who loves these kind of films. rachel is fantastic, olivia is amazing and for emma, my queen deserves a oscar nom because I feel like this is a much better performance than la la land. I can’t not wait to see this again with a much bigger crowd." likes={ 94 }/>
		</div>
		}
}